package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ReadStatus;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id @GeneratedValue
    @Column(name = "noti_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime datetime;

    private String content;

    @Enumerated(EnumType.STRING)
    private ReadStatus isRead; // 읽음 상태 저장 [ READ,UNREAD]

//    private NoticeType type;


}
