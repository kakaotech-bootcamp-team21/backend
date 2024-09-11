package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.jwt.JwtUtil;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;



@Tag(name = "User API", description = "유저 정보 관련 기능 제공 API")
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager; // AuthenticationManager 주입

    @Operation(summary = "https healtch check API", description = "aws 에서 ssl 인증서를 발급받기 위해 상태확인을 하는데 사용하는 api 입니다. 인스턴스가 연결되어있어 자동으로 확인을 합니다.")
    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }

    @Operation(summary = "유저 정보 조회 API", description = "특정 유저 ID를 입력하면, 해당 유저의 정보를 반환합니다.")
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getNickname(),
                        user.getImg(),
                        user.getRole().name(),
                        user.getProfile(),
                        user.getUsername(),
                        user.getPassword() // 테스트 환경이므로 패스워드 포함
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "유저 등록 API", description = "회원가입할 때 유저의 정보를 저장합니다.")
    @PostMapping("/api/users/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        User user = userService.registerUser(userRegisterDto);
        return ResponseEntity.ok(new UserDto(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getImg(),
                user.getRole().name(),
                user.getProfile(),
                user.getUsername(),
                user.getPassword() // 테스트 환경이므로 패스워드 포함
        ));
    }

    @Operation(summary = "유저 로그인 API", description = "로그인할 때 입력받은 데이터가 DB에 저장된 유저의 정보와 같은지 확인합니다.")
    @PostMapping("/api/users/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        User user = userService.loginUser(userLoginDto);

        // Spring Security의 Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createToken(authentication);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(3600); // 1시간
        cookie.setPath("/");
        response.addCookie(cookie);

        response.setHeader("Authorization", "Bearer " + jwt);  // JWT를 헤더에 추가

        return ResponseEntity.ok(new UserDto(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getImg(),
                user.getRole().name(),
                user.getProfile(),
                user.getUsername(),
                user.getPassword() // 테스트 환경이므로 패스워드 포함
        ));
    }

    @Operation(summary = "유저 프로필 업데이트 API", description = "회원가입하고 로그인까지 완료한 유저가 본인의 소개를 위해 마이페이지에서 프로필 정보를 입력할 때 사용합니다.")
    @PutMapping("/api/users/profile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
        User updatedUser = userService.updateUserProfile(userProfileDto);
        return ResponseEntity.ok(new UserDto(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getNickname(),
                updatedUser.getImg(),
                updatedUser.getRole().name(),
                updatedUser.getProfile(),
                updatedUser.getUsername(),
                updatedUser.getPassword() // 테스트 환경이므로 패스워드 포함
        ));
    }


    @Operation(summary = "JWT 테스트 API", description = "토큰을 생성하고 확인하기 위한 API")
    @PostMapping("/login/jwt")
    @ResponseBody
    public ResponseEntity<String> loginJWT(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        try {
            // 사용자 조회
            User user = userService.loadUserByUsername(userLoginDto.getEmail()); // 이메일을 통해 사용자 조회
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
            }

            // 인증 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, userLoginDto.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // JWT 생성
            String jwt = jwtUtil.createToken(authentication);

            // JWT를 쿠키로 설정
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // HTTPS를 위해
            cookie.setMaxAge(3600); // 1시간
            cookie.setPath("/");
            response.addCookie(cookie);

            // JWT를 응답 본문에도 포함
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
//    public ResponseEntity<String> loginJWT(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
//        try {
//            // 사용자 인증
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
//            );
//
//
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // JWT 생성
//            String jwt = jwtUtil.createToken(authentication);
//
//            // JWT를 쿠키로 설정
//            Cookie cookie = new Cookie("jwt", jwt);
//            cookie.setHttpOnly(true);
//            cookie.setSecure(true); // HTTPS를 위해
//            cookie.setMaxAge(3600); // 1시간
//            cookie.setPath("/");
//            response.addCookie(cookie);
//
//            // JWT를 응답 본문에도 포함
//            return ResponseEntity.ok(jwt);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
//        }
//    }
}