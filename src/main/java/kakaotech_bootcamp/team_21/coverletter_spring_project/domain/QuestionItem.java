package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class QuestionItem {

    @Id @GeneratedValue
    @Column(name="question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

    private String question;

    @Lob
    private String content;

    @Column(name = "orders")
    private Integer order;

    // -- 생성자 -- //
    public QuestionItem(String question, String content, Integer order) {
        this.question = question;
        this.content = content;
        this.order = order;
    }

    // -- 연관 관계 메서드 -- //
    public void addReview(Review review) {
        this.review=review;
    }

    // -- 비즈니스 로직 -- //

}
