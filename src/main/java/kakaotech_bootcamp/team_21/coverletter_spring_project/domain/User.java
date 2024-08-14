package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ReadStatus;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;

@Entity
@Table(name="users")
public class User {

    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String nickname;

    private String img;

    @Enumerated(EnumType.STRING)
    private Role role; // 역할 저장 [ USER, SPECIAL, ADMIN ]
}
