package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name="users")
public class User {

    @Id @GeneratedValue
    @Column(name="user_id")
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

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // -- 생성자 -- //
    public User(String username, String nickname, String img, Role role, String profile, String email, String password) {
        this.username = username;
        this.nickname = nickname;
        this.img = img;
        this.role = role;
        this.profile = profile;
        this.email = email;
        this.password = password;
    }
    // -- 연관 관계 메서드 -- //
    public void addSpecialist(Specialist specialist) {
        this.specialist=specialist;
    }
    // -- 비즈니스 로직 -- //

}
