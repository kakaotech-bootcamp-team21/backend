package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
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

@Tag(name = "Industry API", description = "산업군 관련 기능 제공 API")
@RestController
@RequiredArgsConstructor
public class IndustryApiController {

    private final IndustryRepo industryRepo;
    private final UserRepo userRepo;

    @Operation(summary = "특정 산업군 관련 전문가 조회 API",description = "특정 산업 ID를 입력하면, 관련된 전문가들 정보를 반환 해준다.")
    @GetMapping(value = "/api/industries/{id}/specialUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleSpecialUsersDto retrieveRelatedSpecialists(@PathVariable("id") Long industryId) {

        //특정 산업군 ID에 대응하는 전문가들을 가져오고, 전문가들에 대응하는 유저 정보를 가져오는 로직
        List<Specialist> specialists = industryRepo.findRelatedSpecialistsById(industryId);
        List<Long> specialistIds = specialists.stream().map(sp -> sp.getId()).collect(Collectors.toList());
        List<User> specialUsers = userRepo.findAllBySpecialistIds(specialistIds);

        //DTO 생성 작업
        List<CategoryApiController.SimpleSpecialUserDto> bucket = new ArrayList<>();
        for (User sp : specialUsers) {
            bucket.add(new CategoryApiController.SimpleSpecialUserDto(sp.getId(), sp.getNickname(), sp.getProfile(), sp.getSpecialist().getIndustry().getName(), sp.getSpecialist().getOccupation(), sp.getImg()));
        }
        return new SimpleSpecialUsersDto(bucket);

    }

    @Data
    @AllArgsConstructor
    static class SimpleSpecialUsersDto {
        private List<CategoryApiController.SimpleSpecialUserDto> specialists;
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

}
