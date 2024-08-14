package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChattingRoom {

    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="req_id")
    private User req_user;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="res_id")
    private User res_user;

    private LocalDateTime datetime;

//    private ChattingRoomType type;
}
