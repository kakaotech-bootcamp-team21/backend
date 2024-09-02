package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Category API", description = "카테고리 관련 기능 제공 API")
@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;
    private final UserRepo userRepo;

    @Operation(summary = "모든 카테고리 정보 조회 API",description = "모든 카테고리에 대한 정보들을 반환한다.")
    @GetMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleCategoriesDto retrieveAllCategories() {
        List<SimpleCategoryDto> bucket = new ArrayList<>();

        for (Category ca : categoryService.getAllCategory()) {
            bucket.add(new SimpleCategoryDto(ca.getId(), ca.getName()));
        }
        return new SimpleCategoriesDto(bucket);
    }

    @Operation(summary = "특정 카테고리 관련 산업 조회 API",description = "특정 카테고리 id를 입력하면, 관련된 산업 정보들을 반환한다.")
    @GetMapping(value = "/api/categories/{id}/industries", produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleIndustriesDto retrieveRelatedIndustries(@PathVariable("id") Long categoryId) {

        List<SimpleIndustryDto> bucket = new ArrayList<>();
        for (Industry industry : categoryService.getRelatedIndustries(categoryId)) {
            bucket.add(new SimpleIndustryDto(industry.getId(), industry.getName()));
        }

        return new SimpleIndustriesDto(bucket);
    }

    @Operation(summary = "특정 카테고리 관련 전문가 조회 API",description = "특정 카테고리 id를 입력하면, 관련된 전문가 정보들을 반환한다.")
    @GetMapping(value = "/api/categories/{id}/specialUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleSpecialUsersDto retrieveRelatedSpecialists(@PathVariable("id") Long categoryId) {

        //특정 카테고리 ID에 대응하는 전문가들을 가져오고, 전문가들에 대응하는 유저 정보를 가져오는 로직
        List<Specialist> specialists = categoryService.getRelatedSpecialists(categoryId);
        List<Long> specialistIds = specialists.stream().map(sp -> sp.getId()).collect(Collectors.toList());
        List<User> specialUsers = userRepo.findAllBySpecialistIds(specialistIds);

        //DTO 생성 작업
        List<SimpleSpecialUserDto> bucket = new ArrayList<>();
        for (User sp : specialUsers) {
            bucket.add(new SimpleSpecialUserDto(sp.getId(), sp.getNickname(), sp.getProfile(), sp.getSpecialist().getIndustry().getName(), sp.getSpecialist().getOccupation(), sp.getImg()));
        }
        return new SimpleSpecialUsersDto(bucket);
    }

    @Data
    @AllArgsConstructor
    static class SimpleSpecialUsersDto {
        private List<SimpleSpecialUserDto> specialists;
    }

    @Data
    @AllArgsConstructor
    static class SimpleSpecialUserDto {
        private Long id;
        private String nickname;
        private String profile;
        private String industry;
        private String occupation;
        private String img;
    }

    @Data
    @AllArgsConstructor
    static class SimpleCategoriesDto {
        private List<SimpleCategoryDto> categories;
    }

    @Data
    @AllArgsConstructor
    static class SimpleCategoryDto {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class SimpleIndustriesDto {
        private List<SimpleIndustryDto> industries;
    }

    @Data
    @AllArgsConstructor
    static class SimpleIndustryDto {

        private Long id;
        private String name;
    }

}
