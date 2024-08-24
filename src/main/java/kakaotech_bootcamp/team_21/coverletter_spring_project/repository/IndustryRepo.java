package kakaotech_bootcamp.team_21.coverletter_spring_project.repository;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryRepo extends JpaRepository<Industry,Long> {
}
