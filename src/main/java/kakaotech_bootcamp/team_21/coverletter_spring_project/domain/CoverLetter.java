package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.CoverLetterType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.file.UploadFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CoverLetter {

    @Id @GeneratedValue
    @Column(name = "coverletter_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id",nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    private CoverLetterType type;

    private Boolean isOpen;

    private String company;

    private String postition;

    @Embedded
    private UploadFile coverLetter;

    // -- 생성자 -- //
    public CoverLetter(CoverLetterType type, Boolean isOpen, String company, String postition, UploadFile coverLetter) {
        this.type = type;
        this.isOpen = isOpen;
        this.company = company;
        this.postition = postition;
        this.coverLetter = coverLetter;
    }

    // -- 연관 관계 메서드 -- //
    public void addUser(User user) {
        this.user=user;
    }
    // -- 비즈니스 로직 -- //
}
