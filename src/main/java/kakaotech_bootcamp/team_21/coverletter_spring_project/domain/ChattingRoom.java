package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ChattingRoomType;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class ChattingRoom {

    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="request_id",nullable = false)
    private Request request;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sp_time_id",nullable = false)
    private SpecialistTime specialistTime;

    @Enumerated(EnumType.STRING)
    private ChattingRoomType type;

    // -- 생성자 -- //
    public ChattingRoom(ChattingRoomType type) {
        this.type = type;
    }

    // -- 연관 관계 메서드 -- //
    public void addRequest(Request request) {
        this.request = request;
    }
    public void addSpecialistTime(SpecialistTime specialistTime) {
        this.specialistTime=specialistTime;
    }

    // -- 비즈니스 로직 -- //
}
