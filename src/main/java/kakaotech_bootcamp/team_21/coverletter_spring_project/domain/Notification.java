package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.NoticeType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Notification {

    @Id @GeneratedValue
    @Column(name = "noti_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime datetime;

    private String content;

    private Boolean isRead;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    // -- 생성자 -- //
    public Notification(LocalDateTime datetime, String content, Boolean isRead, NoticeType type) {
        this.datetime = datetime;
        this.content = content;
        this.isRead = isRead;
        this.type = type;
    }

    // -- 연관 관계 메서드 -- //
    public void addUser(User user) {
        this.user=user;
    }
    // -- 비즈니스 로직 -- //
}
