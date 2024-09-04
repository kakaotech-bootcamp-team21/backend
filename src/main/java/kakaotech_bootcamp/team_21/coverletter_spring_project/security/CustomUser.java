package kakaotech_bootcamp.team_21.coverletter_spring_project.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class CustomUser extends User {
    private String displayName;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String displayName) {
        super(username, password, authorities);
        this.displayName = displayName;
    }

    // 필요에 따라 추가 메서드를 구현할 수 있습니다.
}
