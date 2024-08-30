package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserLoginDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserProfileDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserRegisterDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "유저 등록 api", description = "회원가입할때 유저의 정보를 저장함")
    @PostMapping("/api/users/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        User user = userService.registerUser(userRegisterDto);
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
    }
    @Operation(summary = "유저 로그인 api", description = "로그인할떄 입력받은 데이터가 디비에 저장된 유저의 정보와 같은지 확인함")
    @PostMapping("/api/users/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.loginUser(userLoginDto);
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
    }

    @Operation(summary = "유저 프로필 작성 관련 api", description = "회원가입하고 로그인까지 완료한 유저가 본인의 소개를 위해 마이페이지에서 프로필 정보를 입력할때 사용함")
    // 프로필 업데이트 엔드포인트
    @PutMapping("/api/users/profile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
        User updatedUser = userService.updateUserProfile(userProfileDto);
        UserDto userDto = new UserDto(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getNickname(),
                updatedUser.getImg(),
                updatedUser.getRole().name(),
                updatedUser.getProfile(),
                updatedUser.getEmail(),
                null  // 패스워드는 응답에 포함하지 않음
        );
        return ResponseEntity.ok(userDto);
    }
}
