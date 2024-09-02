package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserLoginDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserProfileDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserRegisterDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Optional<User> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

    public User registerUser(UserRegisterDto userRegisterDto) {
        User user = new User(
                userRegisterDto.getUsername(),
                userRegisterDto.getNickname(),
                userRegisterDto.getImg(),
                userRegisterDto.getRole(),
                "",
                userRegisterDto.getEmail(),
                userRegisterDto.getPassword()
        );
        return userRepo.save(user);
    }

    public User loginUser(UserLoginDto userLoginDto) {
        logger.info("로그인 시도: {}", userLoginDto.getEmail());

        User user = (User) userRepo.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> {
                    logger.error("사용자 찾을 수 없음: {}", userLoginDto.getEmail());
                    return new RuntimeException("User not found");
                });

        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            logger.error("비밀번호 불일치: {}", userLoginDto.getEmail());
            throw new RuntimeException("Invalid password");
        }

        logger.info("로그인 성공: {}", user.getUsername());
        return user;
    }

    public User updateUserProfile(UserProfileDto userProfileDto) {
        User user = userRepo.findById(userProfileDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setProfile(userProfileDto.getProfile());
        return userRepo.save(user);
    }
}