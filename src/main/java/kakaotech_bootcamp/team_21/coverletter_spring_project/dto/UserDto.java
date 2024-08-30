package kakaotech_bootcamp.team_21.coverletter_spring_project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String nickname;
    private String img;
    private String role;
    private String profile;
    private String email;
//    개발 환경에서만 일단 만들어놈
    private String password;
}
