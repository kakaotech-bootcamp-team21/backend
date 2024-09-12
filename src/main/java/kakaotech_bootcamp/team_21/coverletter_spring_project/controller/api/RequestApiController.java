package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.CoverLetter;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Portfolio;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Request;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.CoverLetterType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.RequestType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.dto.QuestionItemDto;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.UserRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.CoverLetterService;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.PortfolioService;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.RequestService;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Tag(name = "첨삭요청 API", description = "첨삭요청 관련 기능 제공 API")
@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestApiController {

    private final S3Service s3Service;
    private final CoverLetterService coverLetterService;
    private final PortfolioService portfolioService;
    private final UserRepo userRepo;
    private final RequestService requestService;

    @Operation(summary = "AI_MAKE 유형 첨삭 요청 생성 API",description = "AI_MAKE 유형의 첨삭요청을 생성한다.")
    @PostMapping(value = "/ai_make",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRequestTypeOfAiMake(@Parameter(description = "포트폴리오는 1개만 첨부 가능.") @RequestPart(required = false) MultipartFile file,
                                                    @RequestParam Long userId,
                                                    @Parameter(description = "ex) [ \"string1\", \"string2\", ]") @RequestPart(name="questions",required = false) List<String> questions) throws IOException {

        try {
            CoverLetter coverLetter = coverLetterService.createAiMakeCoverLetter(null, userId, questions);
            Portfolio portfolio=null;
            if (file != null) {
                portfolio = portfolioService.createPortfolio(userId, file);
            }
            User reqUser = userRepo.findById(userId).get();

            Request save = requestService.createRequestTypeOfAiMake(coverLetter, portfolio,reqUser);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "AI_UPGRADE 유형 첨삭 요청 생성 API",description = "AI_UPGRADE 유형의 첨삭요청을 생성한다.")
    @PostMapping(value = "/ai_upgrade",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRequestTypeOfAiUpgrade(@Parameter(description = "자기소개서는 1개만 첨부 가능.") @RequestPart(required = false) MultipartFile file,
                                                       @RequestParam Long userId,
                                                       @Parameter(description = "ex) [ { \"question\": \"string\", \"answer\": \"string\" } , ]") @RequestPart(name="questionItems",required = false) List<QuestionItemDto> itemsDto) throws IOException {

        try {
            CoverLetter coverLetter = coverLetterService.createAiUpgradeOrBasicCoverLetter(file,userId,itemsDto,CoverLetterType.AI_UPGRADE);

            User reqUser = userRepo.findById(userId).get();

            Request save = requestService.createRequestTypeOfAiUpgrade(coverLetter,reqUser);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @Operation(summary = "BASIC 유형 첨삭 요청 생성 API",description = "BASIC(일반,채팅,영상채팅용) 유형의 첨삭요청을 생성한다.")
    @PostMapping(value = "/basic",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRequestTypeOfBasic(@Parameter(description = "자기소개서는 1개만 첨부 가능.") @RequestPart(required = false) MultipartFile file,
                                                   @RequestParam Long reqUserId,
                                                   @RequestParam Long resUserId,
                                                   @Parameter(description ="ex) BASIC,CHAT,VIDEO")@RequestParam String requestType,
                                                   @Parameter(description = "ex) [ { \"question\": \"string\", \"answer\": \"string\" } , ]") @RequestPart(name="questionItems",required = false) List<QuestionItemDto> itemsDto) throws IOException {


        try {
            CoverLetter coverLetter = coverLetterService.createAiUpgradeOrBasicCoverLetter(file,reqUserId,itemsDto,CoverLetterType.BASIC);

            User reqUser = userRepo.findById(reqUserId).get();
            User resUser = userRepo.findById(resUserId).get();

            if (resUser==null || reqUser==null) {
               throw new RuntimeException("no reqUser or resUser");
            }

            Request save = requestService.createRequestTypeOfBasic(coverLetter,RequestType.valueOf(requestType.toUpperCase()),reqUser,resUser);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
