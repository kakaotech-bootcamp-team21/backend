package kakaotech_bootcamp.team_21.coverletter_spring_project.dto;

//유저 프로필은 가입할 때 바로 작성 안하고, 나중에 로그인 하고 나서 마이페이지 같은곳에서 작성하므로 따로 dto 를 분리시켰다.
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDto {
    private Long userId;  // 사용자 ID
    private String profile;  // 사용자가 입력한 프로필 정보
}
