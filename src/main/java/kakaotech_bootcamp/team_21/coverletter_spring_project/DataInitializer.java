package kakaotech_bootcamp.team_21.coverletter_spring_project;

import jakarta.annotation.PostConstruct;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepository;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepository;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final IndustryRepository industryRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {

        // -- User -- //
        userRepository.save(new User("yohan", "water", "/img/apple.jpg", Role.USER, "저는 워터입니다."));
        userRepository.save(new User("hanyo", "whater", "/img/melon.jpg", Role.USER, "저는 와터입니다."));
        userRepository.save(new User("kakao", "winter", "/img/banana.jpg", Role.USER, "저는 카카오입니다."));


        // -- 카테고리 start -- //

        List<Industry> industryList=new ArrayList<>();
        Category root=new Category("모든 분야");
        categoryRepository.save(root);

        // 경영 및 리더십 (Management & Leadership)
        Industry item1=new Industry("기업 경영");
        industryRepository.save(item1);
        industryList.add(item1);

        Industry item2=new Industry("스타트업 및 창업");
        industryRepository.save(item2);
        industryList.add(item2);

        Industry item3=new Industry("프로젝트 관리");
        industryRepository.save(item3);
        industryList.add(item3);

        Industry item4=new Industry("리더쉽 개발");
        industryRepository.save(item4);
        industryList.add(item4);

        Industry item5=new Industry("조직문화 및 혁신");
        industryRepository.save(item5);
        industryList.add(item5);

        Industry item6=new Industry("전략 기획");
        industryRepository.save(item6);
        industryList.add(item6);

        Industry item7=new Industry("HR 및 인사관리");
        industryRepository.save(item7);
        industryList.add(item7);

        Category managementAndLeadership = new Category("경영 및 리더쉽");
        managementAndLeadership.addParentCategory(root);
        managementAndLeadership.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepository.save(managementAndLeadership);

        industryList.clear();

        // 기술 및 IT (Technology & IT)
        Industry item8=new Industry("소프트웨어 개발");
        industryRepository.save(item8);
        industryList.add(item8);

        Industry item9=new Industry("데이터 사이언스 및 인공지능");
        industryRepository.save(item9);
        industryList.add(item9);

        Industry item10=new Industry("사이버 보안");
        industryRepository.save(item10);
        industryList.add(item10);

        Industry item11=new Industry("IT 인프라 및 네트워킹");
        industryRepository.save(item11);
        industryList.add(item11);

        Industry item12=new Industry("핀테크");
        industryRepository.save(item12);
        industryList.add(item12);

        Industry item13=new Industry("블록체인");
        industryRepository.save(item13);
        industryList.add(item13);

        Industry item14=new Industry("모바일 애플리케이션 개발");
        industryRepository.save(item14);
        industryList.add(item14);

        Category technologyAndIT = new Category("기술 및 IT");
        technologyAndIT.addParentCategory(root);
        technologyAndIT.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepository.save(technologyAndIT);

        industryList.clear();

        // 마케팅 및 커뮤니케이션 (Marketing & Communication)
        Industry item15=new Industry("디지털 마케팅");
        industryRepository.save(item15);
        industryList.add(item15);

        Industry item16=new Industry("브랜드 관리");
        industryRepository.save(item16);
        industryList.add(item16);

        Industry item17=new Industry("콘텐츠 마케팅");
        industryRepository.save(item17);
        industryList.add(item17);

        Industry item18=new Industry("광고 및 PR");
        industryRepository.save(item18);
        industryList.add(item18);

        Industry item19=new Industry("시장 조사 및 분석");
        industryRepository.save(item19);
        industryList.add(item19);

        Industry item20=new Industry("고객 관계 관리");
        industryRepository.save(item20);
        industryList.add(item20);

        Industry item21=new Industry("소셜 미디어 전략");
        industryRepository.save(item21);
        industryList.add(item21);

        Category marketingAndCommunication = new Category("마케팅 및 커뮤니케이션");
        marketingAndCommunication.addParentCategory(root);
        marketingAndCommunication.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepository.save(marketingAndCommunication);

        industryList.clear();

        // 금융 및 경제 (Finance & Economics)
        Industry item22=new Industry("투자 및 자산 관리");
        industryRepository.save(item22);
        industryList.add(item22);

        Industry item23=new Industry("기업 금융");
        industryRepository.save(item23);
        industryList.add(item23);

        Industry item24=new Industry("경제 분석 및 정책");
        industryRepository.save(item24);
        industryList.add(item24);

        Industry item25=new Industry("금융 기술");
        industryRepository.save(item25);
        industryList.add(item25);

        Industry item26=new Industry("회계 및 감사");
        industryRepository.save(item26);
        industryList.add(item26);

        Industry item27=new Industry("은행 및 보험");
        industryRepository.save(item27);
        industryList.add(item27);

        Industry item28=new Industry("재무 계획");
        industryRepository.save(item28);
        industryList.add(item28);

        Category financeAndEconomics = new Category("금융 및 경제");
        financeAndEconomics.addParentCategory(root);
        financeAndEconomics.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepository.save(financeAndEconomics);

        industryList.clear();

        // 창의성 및 디자인 (Creativity & Design)
        Industry item29=new Industry("그래픽 디자인");
        industryRepository.save(item29);
        industryList.add(item29);

        Industry item30=new Industry("UX/UI 디자인");
        industryRepository.save(item30);
        industryList.add(item30);

        Industry item31=new Industry("광고 크리에이티브");
        industryRepository.save(item31);
        industryList.add(item31);

        Industry item32=new Industry("제품 디자인");
        industryRepository.save(item32);
        industryList.add(item32);

        Industry item33=new Industry("패션 디자인");
        industryRepository.save(item33);
        industryList.add(item33);

        Industry item34=new Industry("영상 및 사진");
        industryRepository.save(item34);
        industryList.add(item34);

        Industry item35=new Industry("게임 디자인");
        industryRepository.save(item35);
        industryList.add(item35);

        Category creativityAndDesign = new Category("창의성 및 디자인");
        creativityAndDesign.addParentCategory(root);
        creativityAndDesign.addIndustryList(industryList);
        root.addIndustryList(industryList);
        categoryRepository.save(creativityAndDesign);

        industryList.clear();

        categoryRepository.save(root);


        // -- 카테고리 End -- //





    }
}
