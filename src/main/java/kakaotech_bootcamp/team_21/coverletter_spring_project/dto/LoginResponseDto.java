package kakaotech_bootcamp.team_21.coverletter_spring_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private UserDto user;
    private String token;
}
