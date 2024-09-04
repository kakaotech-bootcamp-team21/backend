package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.CoverLetter;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.QuestionItem;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.CoverLetterType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.S3Type;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.file.UploadFile;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.QuestionItemDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CoverLetterRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.QuestionItemRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CoverLetterService {

    private final UserRepo userRepo;
    private final S3Service s3Service;
    private final CoverLetterRepo coverLetterRepo;
    private final QuestionItemRepo questionItemRepo;

    public CoverLetter createAiMakeCoverLetter(MultipartFile file, Long userId, List<String> questions) throws IOException {
        CoverLetter coverletter=new CoverLetter(CoverLetterType.AI_MAKE,false,null,null,null);

        User user = userRepo.findById(userId).get();

        if(user!=null)coverletter.addUser(user);

        if (file != null) {
            String url = s3Service.uploadFile(file, S3Type.COVERLETTER);
            UploadFile uploadFile=new UploadFile(file.getOriginalFilename(),s3Service.extractFileNameFromUrl(url));
            coverletter.setCoverLetter(uploadFile);
        }

        CoverLetter save = coverLetterRepo.save(coverletter);

        if (questions != null) {
            for (int i = 0; i < questions.size(); i++) {
                QuestionItem questionItem = new QuestionItem(questions.get(i), null, i+1);
                questionItem.addCoverLetter(save);
                questionItemRepo.save(questionItem);
            }
        }

        return save;
    }

    public CoverLetter createAiUpgradeOrBasicCoverLetter(MultipartFile file, Long userId, List<QuestionItemDto> itemsDto,CoverLetterType type) throws IOException {
        CoverLetter coverletter=new CoverLetter(type,false,null,null,null);

        User user = userRepo.findById(userId).get();

        if(user!=null)coverletter.addUser(user);

        if (file != null) {
            String url = s3Service.uploadFile(file, S3Type.COVERLETTER);
            UploadFile uploadFile=new UploadFile(file.getOriginalFilename(),s3Service.extractFileNameFromUrl(url));
            coverletter.setCoverLetter(uploadFile);
        }

        CoverLetter save = coverLetterRepo.save(coverletter);

        if (itemsDto != null) {
            for (int i = 0; i < itemsDto.size(); i++) {
                QuestionItem questionItem = new QuestionItem(itemsDto.get(i).getQuestion(), itemsDto.get(i).getAnswer(), i+1);
                questionItem.addCoverLetter(save);
                questionItemRepo.save(questionItem);
            }
        }

        return save;
    }
}
