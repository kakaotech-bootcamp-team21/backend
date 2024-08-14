package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Chatting {

    @Id @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChattingRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    private LocalDateTime datetime;

    private String content;
}
