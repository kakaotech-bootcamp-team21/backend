package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ReviewType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "req_id")
    private User reqUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_id")
    private User resUser;

    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    private ReviewType type;

    // -- 생성자 -- //
    public Review(LocalDateTime datetime, ReviewType type) {
        this.datetime = datetime;
        this.type = type;
    }
}
