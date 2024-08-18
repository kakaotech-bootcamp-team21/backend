package kakaotech_bootcamp.team_21.coverletter_spring_project;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.testDomain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kakaotech_bootcamp.team_21.coverletter_spring_project.domain.testDomain.QMember.member;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class QueryDSLTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;// QueryDSL에서 공통적으로 사용하는 것을 클래스 프로퍼티로 지정.

    @BeforeEach
    public void before() {

        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }


    @Test
    public void QueryDSLTest() throws Exception {//기본 실행 검증 (1. Querydsl Q타입이 정상 동작하는가? 2. lombok이 정상 동작 하는가?)

        Hello hello = new Hello();
        em.persist(hello);
        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = QHello.hello; //Querydsl Q타입 동작 확인
        Hello result = query
                .selectFrom(qHello)
                .fetchOne();
        Assertions.assertThat(result).isEqualTo(hello);

        //lombok 동작 확인 (hello.getId())
        Assertions.assertThat(result.getId()).isEqualTo(hello.getId());

    }

    //-- Querydsl vs JPQL -- //

    @Test
    public void startJPQL() {// JPQL로 member1을 찾아라!

        String qlString =
        "select m from Member m " +
                "where m.username = :username";
        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")

                .getSingleResult();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {// QueryDSL로 member1을 찾아라!

//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m");
        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))//파라미터 바인딩 처리
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // -- 기본 Q-Type 활용 -- //
    @Test
    public void startQuerydsl3() {
// -- Q 클래스 인스턴스를 사용하는 2가지 방법 -- //
//        QMember qMember = new QMember("m");//별칭 직접 지정
//        QMember qMember = QMember.member; //기본 인스턴스 사용

        // 기본 인스턴스를 static import와 함께 사용
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // -- 검색 조건 쿼리 -- //
    @Test
    public void search() { // 검색 조건은 '.and()' , '.or()' 를 메서드 체인으로 연결할 수 있다.
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
    // -- JPQL이 제공하는 모든 검색 조건 제공 -- //
//    member.username.eq("member1") // username = 'member1'
//    member.username.ne("member1") //username != 'member1'
//    member.username.eq("member1").not() // username != 'member1'
//    member.username.isNotNull() //이름이 is not null
//    member.age.in(10, 20) // age in (10,20)
//    member.age.notIn(10, 20) // age not in (10, 20)
//    member.age.between(10,30) //between 10, 30
//    member.age.goe(30) // age >= 30
//    member.age.gt(30) // age > 30
//    member.age.loe(30) // age <= 30
//    member.age.lt(30) // age < 30
//    member.username.like("member%") //like 검색 member.username.contains("member") // like ‘%member%’ 검색 member.username.startsWith("member") //like ‘member%’ 검색


    // -- AND 조건을 파라미터로 처리 -- //
    @Test
    public void searchAndParam() {
        // where() 에 파라미터로 검색조건을 추가하면 `AND` 조건이 추가됨
        // 이경우 `null` 값은무시 메서드추출을활용해서동적쿼리를 깔끔하게 만들 수 있음
        List<Member> result1 = queryFactory
        .selectFrom(member)
       .where(member.username.eq("member1"),
        member.age.eq(10))
        .fetch();
        assertThat(result1.size()).isEqualTo(1);
    }

    // -- 결과 조회 -- //
//`fetch()` : 리스트 조회, 데이터 없으면 빈 리스트 반환
//`fetchOne()` : 단 건 조회
//    결과가 없으면 : `null`
//    결과가 둘 이상이면 : `com.querydsl.core.NonUniqueResultException`
//`fetchFirst()` : `limit(1).fetchOne`
//`fetchResults()` : 페이징 정보 포함, total count 쿼리 추가 실행
//`fetchCount()` : count 쿼리로 변경해서 count 수 조회

//    //List
//    List<Member> fetch = queryFactory
//            .selectFrom(member)
//            .fetch();
//    //단 건
//    Member findMember1 = queryFactory
//            .selectFrom(member)
//            .fetchOne();
//    //처음 한 건 조회
//    Member findMember2 = queryFactory
//            .selectFrom(member)
//            .fetchFirst();
//    //페이징에서 사용
//    QueryResults<Member> results = queryFactory
//            .selectFrom(member)
//            .fetchResults();
//    //count 쿼리로 변경
//    long count = queryFactory
//            .selectFrom(member)
//            .fetchCount();


    // -- 정렬 -- //
    /**
     *회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 올림차순(asc)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(nulls last) */
    @Test
    public void sort() {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();
        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);
        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }
//    `desc()` , `asc()` : 일반 정렬
//    `nullsLast()` , `nullsFirst()` : null 데이터 순서 부여

    // -- 페이징 -- //

    // 1. 조회 건수 제한
    @Test
    public void paging1() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) //0부터 시작(zero index)
                .limit(2) //최대 2건 조회
                .fetch();
        assertThat(result.size()).isEqualTo(2);
    }

    // 2. 전체 조회 수가 필요하면?
    @Test
    public void paging2() {
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();
        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }

}
