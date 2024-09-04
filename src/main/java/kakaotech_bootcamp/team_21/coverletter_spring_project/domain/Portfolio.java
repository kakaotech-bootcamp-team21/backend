package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.PortfolioDocType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.file.UploadFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Portfolio {

    @Id @GeneratedValue
    @Column(name = "portfolio_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column
    private String portfolioTitle;

    @Embedded
    private UploadFile portfolio;


    // -- 생성자 -- //
    public Portfolio(String portfolioTitle, UploadFile portfolio) {
        this.portfolioTitle = portfolioTitle;
        this.portfolio = portfolio;
    }

    public void addUser(User user) {
        this.user=user;
    }
}
