package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.Role;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserLoginDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserProfileDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.UserRegisterDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

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
        User user = (User) userRepo.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public User updateUserProfile(UserProfileDto userProfileDto) {
        User user = userRepo.findById(userProfileDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setProfile(userProfileDto.getProfile());
        return userRepo.save(user);
    }
}