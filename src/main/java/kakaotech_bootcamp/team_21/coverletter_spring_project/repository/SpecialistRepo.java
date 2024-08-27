package kakaotech_bootcamp.team_21.coverletter_spring_project.repository;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialistRepo extends JpaRepository<Specialist,Long> {

}
