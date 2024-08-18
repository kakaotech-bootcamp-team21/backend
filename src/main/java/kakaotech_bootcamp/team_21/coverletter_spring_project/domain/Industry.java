package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Industry {

    @Id @GeneratedValue
    @Column(name = "industry_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "industries")
    private List<Category> categories=new ArrayList<>();

    // -- 생성자 -- //
    public Industry(String name) {
        this.name=name;
    }
    // -- 비즈니스 로직 -- //
}
