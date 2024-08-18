package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.ChattingRoomType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class ChattingRoom {

    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name="sp_time_id")
    private SpecialistTime specialistTime;

    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    private ChattingRoomType type;

    // -- 생성자 -- //
    public ChattingRoom(LocalDateTime datetime, ChattingRoomType type) {
        this.datetime=datetime;
        this.type=type;
    }

    // -- 연관 관계 메서드 -- //
    public void addReview(Review review) {
        this.review=review;
    }
    public void addSpecialistTime(SpecialistTime specialistTime) {
        this.specialistTime=specialistTime;
    }

    // -- 비즈니스 로직 -- //
}
