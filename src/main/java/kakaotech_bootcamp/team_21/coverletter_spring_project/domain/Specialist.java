package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Specialist {

    @Id @GeneratedValue
    @Column(name = "special_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id",nullable = false)
    private Industry industry;

    private String occupation;

    // -- 생성자 -- //
    public Specialist(String occupation) {
        this.occupation = occupation;
    }

    // -- 연관 관계 메서드 -- //
    public void addIndustry(Industry industry) {
        this.industry=industry;
    }
    // -- 비즈니스 로직 -- //

}
