package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class IndustryService {

    private final IndustryRepo industryRepo;

    public List<Specialist> getRelatedSpecialists(Long industryId) {

        return industryRepo.findRelatedSpecialistsById(industryId);
    }
}
