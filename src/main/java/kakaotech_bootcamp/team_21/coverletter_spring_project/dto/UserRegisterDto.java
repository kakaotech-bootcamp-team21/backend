package kakaotech_bootcamp.team_21.coverletter_spring_project.dto;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterDto {
    private String username;
    private String nickname;
    private String img;
    private String email;
    private String password;
    private Role role;

//    인더스트리 아이디를 선택해야함
    private Long categoryId;  // 카테고리 ID 추가
    private Long industryId;
}

