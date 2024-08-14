package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;

@Entity
public class Specialist {

    @Id @GeneratedValue
    @Column(name = "special_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    private String occupation;
}
