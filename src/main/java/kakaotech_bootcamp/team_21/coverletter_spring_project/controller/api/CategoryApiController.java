package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.CategoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public Map<String, Object> retrieveAllCategories() {
        Map<String,Object> result=new HashMap<>();
        List<SimpleCategoryDto> bucket= new ArrayList<>();

        for (Category ca : categoryService.getAllCategory()) {
            bucket.add(new SimpleCategoryDto(ca.getId(),ca.getName()));
        }

        result.put("categories",bucket);
        return result;
    }

    @GetMapping("/api/categories/{id}")
    public Map<String,Object> retrieveRelatedIndustries(@PathVariable("id") Long categoryId) {

        Map<String,Object> result=new HashMap<>();
        List<SimpleIndustryDto> bucket=new ArrayList<>();
        for (Industry industry : categoryService.getRelatedIndustries(categoryId)) {
            bucket.add(new SimpleIndustryDto(industry.getId(),industry.getName()));
        }
        result.put("industries",bucket);
        return result;
    }
    @Data
    static class SimpleCategoryDto
    {
        private Long id;
        private String name;

        public SimpleCategoryDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    static class SimpleIndustryDto {

        private Long id;
        private String name;

        public SimpleIndustryDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
