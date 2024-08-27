package kakaotech_bootcamp.team_21.coverletter_spring_project.repository;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndustryRepo extends JpaRepository<Industry,Long> {


    Industry findByName(String industryName);

    @Query("select sp from Specialist sp where sp.industry.id= :id ")
    List<Specialist> findRelatedSpecialistsById(@Param("id") Long industryId);
}
