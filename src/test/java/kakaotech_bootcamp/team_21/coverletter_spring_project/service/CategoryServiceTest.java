package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    public void 관련산업가져오기테스트() {

        Category managementAndLeadership = categoryRepo.findByName("경영 및 리더쉽");
        System.out.println("--- my test ---");
        for (Industry industry : managementAndLeadership.getIndustries()) {
            System.out.println(industry.getName());
        }

        Category all = categoryRepo.findByName("모든 분야");
        System.out.println("--- my test2 ---");
        for (Industry industry : all.getIndustries()) {
            System.out.println(industry.getName());
        }
    }

}