package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.RequestType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Request {

    @Id @GeneratedValue
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "req_id")
    private User reqUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_id")
    private User resUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coverletter_id")
    private CoverLetter coverLetter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    private RequestType type;

    // -- 생성자 -- //
    public Request(LocalDateTime datetime, RequestType type) {
        this.datetime = datetime;
        this.type = type;
    }
    // -- 연관 관계 메서드 -- //
    public void addReqUser(User user) {
        this.reqUser=user;
    }
    public void addResUser(User user) {
        this.resUser=user;
    }
    public void addCoverLetter(CoverLetter coverLetter) {
        this.coverLetter=coverLetter;
    }
    public void addPortfolio(Portfolio portfolio) {
        this.portfolio=portfolio;
    }
    // -- 비즈니스 로직 -- //

}
