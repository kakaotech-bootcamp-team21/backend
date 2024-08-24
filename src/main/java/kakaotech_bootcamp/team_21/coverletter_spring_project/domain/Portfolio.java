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

    @Column(nullable = false)
    private String portfolioTitle;

    @Embedded
    private UploadFile portfolio;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PortfolioDocType fileType;

    // -- 생성자 -- //
    public Portfolio(String portfolioTitle, UploadFile portfolio, PortfolioDocType fileType) {
        this.portfolioTitle = portfolioTitle;
        this.portfolio = portfolio;
        this.fileType = fileType;
    }

    public void addUser(User user) {
        this.user=user;
    }
}
