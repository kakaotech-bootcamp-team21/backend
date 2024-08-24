package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class InterviewQuestion {

    @Id @GeneratedValue
    @Column(name = "in_qu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coverletter_id",nullable = false)
    private CoverLetter coverLetter;

    private String question;

    @Lob
    private String answer;
    // -- 생성자 -- //
    public InterviewQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // -- 연관 관계 메서드 -- //
    public void addCoverLetter(CoverLetter coverLetter) {
        this.coverLetter=coverLetter;
    }
    // -- 비즈니스 로직 -- //
}
