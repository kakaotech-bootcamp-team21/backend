package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserLoginDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserProfileDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserRegisterDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.SpecialistRepo;
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
    private final SpecialistRepo specialistRepo;
    private final IndustryRepo industryRepo;
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

//        // 역할이 SPECIAL인지 확인
//        if (userRegisterDto.getRole() == Role.SPECIAL) {
//            Specialist specialist = new Specialist(); // Specialist 객체 생성
//            // 필요한 데이터를 Specialist 객체에 설정해야 함
//            Specialist savedSpecialist = specialistRepo.save(specialist);
//            user.addSpecialist(savedSpecialist);
//        }

        if (userRegisterDto.getRole() == Role.SPECIAL) {
            // 산업군 ID로 산업 정보 가져오기
            Industry industry = industryRepo.findById(userRegisterDto.getIndustryId())
                    .orElseThrow(() -> new RuntimeException("Industry not found"));
            Specialist specialist = new Specialist();
//            Specialist specialist = new Specialist("전문가 직업명"); // 필요한 직업명으로 대체
            specialist.addIndustry(industry);  // 산업군 설정
            Specialist savedSpecialist = specialistRepo.save(specialist);
            user.addSpecialist(savedSpecialist);
        }




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

    public User loadUserByUsername(String email) {
        return userRepo.findByEmail(email)
                .orElse(null);
    }
}