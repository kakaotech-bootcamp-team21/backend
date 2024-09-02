package kakaotech_bootcamp.team_21.coverletter_spring_project.service;


import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.CoverLetter;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Portfolio;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Request;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.RequestType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.RequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class RequestService {

    private final RequestRepo requestRepo;

    public Request createRequestTypeOfAiMake(CoverLetter coverLetter, Portfolio portfolio, RequestType type, User reqUser,User resUser) {

        Request request=new Request(LocalDateTime.now(),type);

        request.addCoverLetter(coverLetter);
        request.addPortfolio(portfolio);
        request.addReqUser(reqUser);
        request.addResUser(resUser);

        Request save = requestRepo.save(request);
        return save;
    }
}
