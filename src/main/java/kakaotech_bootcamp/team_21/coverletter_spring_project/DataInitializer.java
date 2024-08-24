package kakaotech_bootcamp.team_21.coverletter_spring_project;

import jakarta.annotation.PostConstruct;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepo userRepo;
    private final IndustryRepo industryRepo;
    private final CategoryRepo categoryRepo;

    @PostConstruct
    public void init() {

        // -- User -- //
        userRepo.save(new User("yohan", "water", "/img/apple.jpg", Role.USER, "저는 워터입니다.","kakao1@naver.com","1234"));
        userRepo.save(new User("hanyo", "whater", "/img/melon.jpg", Role.USER, "저는 와터입니다.","kakao2@naver.com","2345"));
        userRepo.save(new User("kakao", "winter", "/img/banana.jpg", Role.USER, "저는 카카오입니다.","kakao3@naver.com","3456"));


        // -- 카테고리 start -- //

        List<Industry> industryList=new ArrayList<>();
        Category root=new Category("모든 분야");
        categoryRepo.save(root);

        // 경영 및 리더십 (Management & Leadership)
        Industry item1=new Industry("기업 경영");
        industryRepo.save(item1);
        industryList.add(item1);

        Industry item2=new Industry("스타트업 및 창업");
        industryRepo.save(item2);
        industryList.add(item2);

        Industry item3=new Industry("프로젝트 관리");
        industryRepo.save(item3);
        industryList.add(item3);

        Industry item4=new Industry("리더쉽 개발");
        industryRepo.save(item4);
        industryList.add(item4);

        Industry item5=new Industry("조직문화 및 혁신");
        industryRepo.save(item5);
        industryList.add(item5);

        Industry item6=new Industry("전략 기획");
        industryRepo.save(item6);
        industryList.add(item6);

        Industry item7=new Industry("HR 및 인사관리");
        industryRepo.save(item7);
        industryList.add(item7);

        Category managementAndLeadership = new Category("경영 및 리더쉽");
        managementAndLeadership.addParentCategory(root);
        managementAndLeadership.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(managementAndLeadership);

        industryList.clear();

        // 기술 및 IT (Technology & IT)
        Industry item8=new Industry("소프트웨어 개발");
        industryRepo.save(item8);
        industryList.add(item8);

        Industry item9=new Industry("데이터 사이언스 및 인공지능");
        industryRepo.save(item9);
        industryList.add(item9);

        Industry item10=new Industry("사이버 보안");
        industryRepo.save(item10);
        industryList.add(item10);

        Industry item11=new Industry("IT 인프라 및 네트워킹");
        industryRepo.save(item11);
        industryList.add(item11);

        Industry item12=new Industry("핀테크");
        industryRepo.save(item12);
        industryList.add(item12);

        Industry item13=new Industry("블록체인");
        industryRepo.save(item13);
        industryList.add(item13);

        Industry item14=new Industry("모바일 애플리케이션 개발");
        industryRepo.save(item14);
        industryList.add(item14);

        Category technologyAndIT = new Category("기술 및 IT");
        technologyAndIT.addParentCategory(root);
        technologyAndIT.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(technologyAndIT);

        industryList.clear();

        // 마케팅 및 커뮤니케이션 (Marketing & Communication)
        Industry item15=new Industry("디지털 마케팅");
        industryRepo.save(item15);
        industryList.add(item15);

        Industry item16=new Industry("브랜드 관리");
        industryRepo.save(item16);
        industryList.add(item16);

        Industry item17=new Industry("콘텐츠 마케팅");
        industryRepo.save(item17);
        industryList.add(item17);

        Industry item18=new Industry("광고 및 PR");
        industryRepo.save(item18);
        industryList.add(item18);

        Industry item19=new Industry("시장 조사 및 분석");
        industryRepo.save(item19);
        industryList.add(item19);

        Industry item20=new Industry("고객 관계 관리");
        industryRepo.save(item20);
        industryList.add(item20);

        Industry item21=new Industry("소셜 미디어 전략");
        industryRepo.save(item21);
        industryList.add(item21);

        Category marketingAndCommunication = new Category("마케팅 및 커뮤니케이션");
        marketingAndCommunication.addParentCategory(root);
        marketingAndCommunication.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(marketingAndCommunication);

        industryList.clear();

        // 금융 및 경제 (Finance & Economics)
        Industry item22=new Industry("투자 및 자산 관리");
        industryRepo.save(item22);
        industryList.add(item22);

        Industry item23=new Industry("기업 금융");
        industryRepo.save(item23);
        industryList.add(item23);

        Industry item24=new Industry("경제 분석 및 정책");
        industryRepo.save(item24);
        industryList.add(item24);

        Industry item25=new Industry("금융 기술");
        industryRepo.save(item25);
        industryList.add(item25);

        Industry item26=new Industry("회계 및 감사");
        industryRepo.save(item26);
        industryList.add(item26);

        Industry item27=new Industry("은행 및 보험");
        industryRepo.save(item27);
        industryList.add(item27);

        Industry item28=new Industry("재무 계획");
        industryRepo.save(item28);
        industryList.add(item28);

        Category financeAndEconomics = new Category("금융 및 경제");
        financeAndEconomics.addParentCategory(root);
        financeAndEconomics.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(financeAndEconomics);

        industryList.clear();

        // 창의성 및 디자인 (Creativity & Design)
        Industry item29=new Industry("그래픽 디자인");
        industryRepo.save(item29);
        industryList.add(item29);

        Industry item30=new Industry("UX/UI 디자인");
        industryRepo.save(item30);
        industryList.add(item30);

        Industry item31=new Industry("광고 크리에이티브");
        industryRepo.save(item31);
        industryList.add(item31);

        Industry item32=new Industry("제품 디자인");
        industryRepo.save(item32);
        industryList.add(item32);

        Industry item33=new Industry("패션 디자인");
        industryRepo.save(item33);
        industryList.add(item33);

        Industry item34=new Industry("영상 및 사진");
        industryRepo.save(item34);
        industryList.add(item34);

        Industry item35=new Industry("게임 디자인");
        industryRepo.save(item35);
        industryList.add(item35);

        Category creativityAndDesign = new Category("창의성 및 디자인");
        creativityAndDesign.addParentCategory(root);
        creativityAndDesign.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(creativityAndDesign);

        industryList.clear();

        // 교육 및 학문 (Education & Academia)
        Industry item36=new Industry("고등 교육 및 연구");
        industryRepo.save(item36);
        industryList.add(item36);

        Industry item37=new Industry("교육 기술");
        industryRepo.save(item37);
        industryList.add(item37);

        Industry item38=new Industry("평생 교육 및 직업 교육");
        industryRepo.save(item38);
        industryList.add(item38);

        Industry item39=new Industry("언어 학습");
        industryRepo.save(item39);
        industryList.add(item39);

        Industry item40=new Industry("교육 정책 및 관리");
        industryRepo.save(item40);
        industryList.add(item40);

        Industry item41=new Industry("학습 및 교육 심리");
        industryRepo.save(item41);
        industryList.add(item41);

        Category educationAndAcademia = new Category("교육 및 학문");
        educationAndAcademia.addParentCategory(root);
        educationAndAcademia.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(educationAndAcademia);

        industryList.clear();


        // 과학 및 기술 혁신 (Science & Technology Innovation)
        Industry item42=new Industry("생명 과학 및 생명 공학");
        industryRepo.save(item42);
        industryList.add(item42);

        Industry item43=new Industry("물리학 및 화학");
        industryRepo.save(item43);
        industryList.add(item43);

        Industry item44=new Industry("에너지 및 환경 기술");
        industryRepo.save(item44);
        industryList.add(item44);

        Industry item45=new Industry("우주 과학 및 항공우주");
        industryRepo.save(item45);
        industryList.add(item45);

        Industry item46=new Industry("신소재 및 나노기술");
        industryRepo.save(item46);
        industryList.add(item46);

        Industry item47=new Industry("의료 기술");
        industryRepo.save(item47);
        industryList.add(item47);

        Industry item48=new Industry("로봇 공학 및 자동화");
        industryRepo.save(item48);
        industryList.add(item48);

        Category scienceAndTechnology = new Category("과학 및 기술 혁신");
        scienceAndTechnology.addParentCategory(root);
        scienceAndTechnology.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(scienceAndTechnology);

        industryList.clear();


        // 문화 및 예술 (Culture & Arts)
        Industry item49=new Industry("시각 예술");
        industryRepo.save(item49);
        industryList.add(item49);

        Industry item50=new Industry("음악 및 공연 예술");
        industryRepo.save(item50);
        industryList.add(item50);

        Industry item51=new Industry("영화 및 미디어");
        industryRepo.save(item51);
        industryList.add(item51);

        Industry item52=new Industry("역사 및 인류학");
        industryRepo.save(item52);
        industryList.add(item52);

        Industry item53=new Industry("건축 및 도시 계획");
        industryRepo.save(item53);
        industryList.add(item53);

        Industry item54=new Industry("예술 경영 및 큐레이션");
        industryRepo.save(item54);
        industryList.add(item54);

        Industry item55=new Industry("의료 기술");
        industryRepo.save(item55);
        industryList.add(item55);

        Category curltureAndArts = new Category("문화 및 예술");
        curltureAndArts.addParentCategory(root);
        curltureAndArts.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepo.save(curltureAndArts);

        industryList.clear();

        categoryRepo.save(root);


        // -- 카테고리 End -- //





    }
}
