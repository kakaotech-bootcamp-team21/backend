package kakaotech_bootcamp.team_21.coverletter_spring_project;

import jakarta.transaction.Transactional;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SpringDataJPATest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUser() {
        //given
        User user = new User("userA", "nickA", "/img/apple.jpg", Role.USER,"Hello");
        User savedUser = userRepository.save(user);

        //when
        User findUser = userRepository.findById(savedUser.getId()).get();
        //then
        assertThat(findUser.getId()).isEqualTo(user.getId());
        assertThat(findUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(findUser).isEqualTo(user); //JPA 엔티티 동일성 보장
    }

    @Test
    public void basicCRUD() {
        //given
        User user1 = new User("user1", "nick1", "/img/image1.jpg", Role.USER,"Hello");
        User user2 = new User("user2", "nick2", "/img/image2.jpg", Role.USER,"Hello");

        //when
        userRepository.save(user1);
        userRepository.save(user2);

        //then

        //단건 조회 검증
        User findUser1 = userRepository.findById(user1.getId()).get();
        User findUser2 = userRepository.findById(user2.getId()).get();
        assertThat(findUser1).isEqualTo(user1);
        assertThat(findUser2).isEqualTo(user2);
        //리스트 조회 검증
        List<User> all = userRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = userRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        userRepository.delete(user1);
        userRepository.delete(user2);
        long deletedCount = userRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

}
