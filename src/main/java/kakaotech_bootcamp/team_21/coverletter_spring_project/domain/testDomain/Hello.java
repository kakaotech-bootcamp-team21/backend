package kakaotech_bootcamp.team_21.coverletter_spring_project.domain.testDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
public class Hello {//Test Class

    @Id
    @GeneratedValue
    private Long id;
}
