package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Industry {

    @Id @GeneratedValue
    @Column(name = "industry_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "industries")
    private List<Category> categories=new ArrayList<>();
}
