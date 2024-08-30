package kakaotech_bootcamp.team_21.coverletter_spring_project.repository;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query("select u from User u where u.specialist.id IN :Ids")
    public List<User> findAllBySpecialistIds(@Param("Ids") List<Long> specialistIds);

    Optional<Object> findByEmail(String email);
}
