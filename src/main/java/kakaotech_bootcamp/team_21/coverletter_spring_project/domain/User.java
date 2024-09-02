package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_id")

    private Specialist specialist;

    private String username;

    private String nickname;

    private String img;

    @Enumerated(EnumType.STRING)
    private Role role; // 역할 저장 [ USER, SPECIAL, ADMIN ]

    private String profile; // 간단한 자기소개.

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // 생성자
    public User(String username, String nickname, String img, Role role, String profile, String email, String password) {
        this.username = username;
        this.nickname = nickname;
        this.img = img;
        this.role = role;
        this.profile = profile;
        this.email = email;
        this.password = password;
    }

    // 연관 관계 메서드
    public void addSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    // UserDetails 인터페이스 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return this.email; // email을 username으로 사용
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}