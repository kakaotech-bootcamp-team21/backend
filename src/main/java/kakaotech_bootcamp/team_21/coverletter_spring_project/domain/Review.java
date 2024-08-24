package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ReviewType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sp_user_id")
    private User specialUser;//전문가의 유저 엔티티를 연관 시킴!

    @Enumerated(value = EnumType.STRING)
    private ReviewType type;

    private String title;

    @Lob
    private String content;

    // -- 생성자 -- //
    public Review(ReviewType type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }

    // -- 연관 관계 메서드 -- //
    public void addUser(User user) {
        this.user=user;
    }
    public void addSpecialUser(User specialUser) {
        this.specialUser=specialUser;
    }
    // -- 비즈니스 로직 -- //
}
