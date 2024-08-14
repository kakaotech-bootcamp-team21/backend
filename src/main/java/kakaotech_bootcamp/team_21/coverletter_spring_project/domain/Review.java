package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ReviewType;

import java.time.LocalDateTime;

@Entity
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "req_id")
    private User req_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_id")
    private User res_user;

    private LocalDateTime datetime;

    @Lob
    private String question;

    @Enumerated(EnumType.STRING)
    private ReviewType type;
}
