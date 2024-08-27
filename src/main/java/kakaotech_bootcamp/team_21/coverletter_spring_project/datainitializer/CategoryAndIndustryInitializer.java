package kakaotech_bootcamp.team_21.coverletter_spring_project.datainitializer;

import jakarta.annotation.PostConstruct;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryAndIndustryInitializer {

    private final IndustryRepo industryRepo;
    private final CategoryRepo categoryRepo;

    @PostConstruct
    public void init() {

        // -- 카테고리 및 산업 start -- //
        Category root=new Category("모든 분야");
        categoryRepo.save(root);

        String[][] info=new String[][]{// 마지막 제외 항목 : 산업군 명, 마지막 항목 : 산업군을 포함하는 카테고리 명
                {"기업 경영","스타트업 및 창업","프로젝트 관리","리더십 개발","조직문화 및 혁신","전략 기획","HR 및 인사 관리","경영 및 리더십"},
                {"소프트웨어 개발","데이터 사이언스 및 인공지능","사이버 보안","IT 인프라 및 네트워킹","핀테크","블록체인","모바일 애플리케이션 개발","기술 및 IT"},
                {"디지털 마케팅","브랜드 관리","콘텐츠 마케팅","광고 및 PR","시장 조사 및 분석","고객 관계 관리","소셜 미디어 전략","마케팅 및 커뮤니케이션"},
                {"투자 및 자산 관리","기업 금융","경제 분석 및 정책","금융 기술","회계 및 감사","은행 및 보험","재무 계획","금융 및 경제"},
                {"그래픽 디자인","UX/UI 디자인","광고 크리에이티브","제품 디자인","패션 디자인","영상 및 사진","게임 디자인","창의성 및 디자인"},
                {"고등 교육 및 연구","교육 기술","평생 교육 및 직업 교육","언어 학습","교육 정책 및 관리","학습 및 교육 심리","교육 및 학문"},
                {"생명 과학 및 생명 공학","물리학 및 화학","에너지 및 환경 기술","우주 과학 및 항공우주","신소재 및 나노기술","의료 기술","로봇 공학 및 자동화","과학 및 기술 혁신"},
                {"시각 예술","음악 및 공연 예술","영화 및 미디어","역사 및 인류학","건축 및 도시 계획","예술 경영 및 큐레이션","문화 및 예술"}
        };
        for (int i = 0; i < info.length; i++) {
            List<Industry> industryList=new ArrayList<>();

            for (int j = 0; j < info[i].length; j++) {
                if (j != info[i].length - 1) {
                    Industry tmpIndustry=new Industry(info[i][j]);
                    industryRepo.save(tmpIndustry);
                    industryList.add(tmpIndustry);
                } else {
                    Category tmpCategory = new Category(info[i][j]);
                    tmpCategory.addParentCategory(root);
                    tmpCategory.addIndustryList(industryList);
                    root.addIndustryList(industryList);
                    categoryRepo.save(tmpCategory);
                }
            }
        }

        categoryRepo.save(root);
        // -- 카테고리 End -- //





    }
}
