package kakaotech_bootcamp.team_21.coverletter_spring_project;

import jakarta.transaction.Transactional;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SpringDataJPATest {

    @Autowired
    UserRepo userRepo;

    @Test
    public void testUser() {
        //given
        User user = new User("userA", "nickA", "/img/apple.jpg", Role.USER,"Hello","userA@naver.com","1234");
        User savedUser = userRepo.save(user);

        //when
        User findUser = userRepo.findById(savedUser.getId()).get();
        //then
        assertThat(findUser.getId()).isEqualTo(user.getId());
        assertThat(findUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(findUser).isEqualTo(user); //JPA 엔티티 동일성 보장
    }

}
