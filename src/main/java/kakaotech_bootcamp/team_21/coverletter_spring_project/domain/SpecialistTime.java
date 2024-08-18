package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.AppointmentStatus;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.SpecialistTimeType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class SpecialistTime {

    @Id @GeneratedValue
    @Column(name = "sp_time_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_id")
    private Specialist specialist;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private SpecialistTimeType type;

    private Boolean iaActive;

    // -- 생성자 -- //
    public SpecialistTime(LocalDateTime startDatetime, LocalDateTime endDatetime, AppointmentStatus status, SpecialistTimeType type, Boolean iaActive) {
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.status = status;
        this.type = type;
        this.iaActive = iaActive;
    }

    // -- 연관 관계 메서드 -- //
    public void addSpecialist(Specialist specialist) {
        this.specialist=specialist;
    }

    // -- 비즈니스 로직 -- //
}
