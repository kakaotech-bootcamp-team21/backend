package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {

    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id",nullable = false)
    private ChattingRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User writer;

    private LocalDateTime datetime;

    private String content;

    // -- 생성자 -- //
    public Chatting(LocalDateTime datetime, String content) {
        this.datetime = datetime;
        this.content = content;
    }

    // -- 연관 관계 메서드 -- //
    public void addChattingRoom(ChattingRoom chattingRoom) {
        this.room = chattingRoom;
    }
    public void addWriter(User user) {
        this.writer = user;
    }
    // -- 비즈니스 로직 -- //

}
