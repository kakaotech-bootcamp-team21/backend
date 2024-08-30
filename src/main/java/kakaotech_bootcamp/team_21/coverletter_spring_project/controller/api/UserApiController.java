package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "User API", description = "유저 정보 관련 기능 제공 API")
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @Operation(summary = "유저 정보 조회 API", description = "특정 유저 ID를 입력하면, 해당 유저의 정보를 반환합니다.")
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findUserById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getNickname(),
                    user.getImg(),
                    user.getRole().name(),
                    user.getProfile(),
                    user.getEmail(),
                    user.getPassword()
            );
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}