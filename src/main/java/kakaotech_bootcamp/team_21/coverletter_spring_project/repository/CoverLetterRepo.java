package kakaotech_bootcamp.team_21.coverletter_spring_project.repository;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverLetterRepo extends JpaRepository<CoverLetter,Long> {
}
