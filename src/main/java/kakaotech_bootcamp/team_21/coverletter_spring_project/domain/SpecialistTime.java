package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SpecialistTime {

    @Id @GeneratedValue
    @Column(name = "sp_time_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_id")
    private Specialist specialist;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;
}
