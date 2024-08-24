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
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chat_room_id")
    private ChattingRoom room;

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
    public void addChattingRoom(ChattingRoom room) {
        this.room=room;
    }
    // -- 비즈니스 로직 -- //
}
