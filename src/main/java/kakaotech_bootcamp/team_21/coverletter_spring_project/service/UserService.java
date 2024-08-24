package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class UserService {

    private final UserRepo userRepo;


}
