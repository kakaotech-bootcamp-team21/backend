package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Portfolio;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.S3Type;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.file.UploadFile;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.PortfolioRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class PortfolioService {

    private final S3Service s3Service;
    private final UserRepo userRepo;
    private final PortfolioRepo portfolioRepo;

    public Portfolio createPortfolio(Long userId, MultipartFile file) throws IOException {

        Portfolio portfolio=new Portfolio(null,null);

        User user = userRepo.findById(userId).get();

        if(user!=null)portfolio.addUser(user);

        if (file != null) {
            String url = s3Service.uploadFile(file, S3Type.COVERLETTER);
            UploadFile uploadFile=new UploadFile(file.getOriginalFilename(),s3Service.extractFileNameFromUrl(url));
            portfolio.setPortfolio(uploadFile);
        }

        Portfolio save = portfolioRepo.save(portfolio);

        return save;
    }
}
