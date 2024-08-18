package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.FeedbackStatus;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feedback {

    @Id
    @GeneratedValue
    @Column(name = "feedback_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private LocalDateTime datetime;

    private Boolean isTotal;

    @Lob
    private String feedback;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status;

    // -- 생성자 -- //
    public Feedback(LocalDateTime datetime, Boolean isTotal, String feedback, FeedbackStatus status) {
        this.datetime = datetime;
        this.isTotal = isTotal;
        this.feedback = feedback;
        this.status = status;
    }

    // -- 연관 관계 메서드 -- //
    public void addReview(Review review) {
        this.review = review;
    }

    // -- 비즈니스 로직 -- //


}
