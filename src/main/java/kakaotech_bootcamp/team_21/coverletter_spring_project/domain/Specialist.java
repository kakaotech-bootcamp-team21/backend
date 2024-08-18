package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
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

    // -- 생성자 -- //
    public Specialist(String occupation) {
        this.occupation = occupation;
    }

    // -- 연관 관계 메서드 -- //
    public void addUser(User user) {
        this.user=user;
    }

    public void addIndustry(Industry industry) {
        this.industry=industry;
    }
    // -- 비즈니스 로직 -- //

}
