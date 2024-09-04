package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.S3Type;
import kakaotech_bootcamp.team_21.coverletter_spring_project.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "자기소개서 API", description = "자기소개서 관련 기능 제공 API")
@RestController
@RequestMapping("/api/coverletters")
@RequiredArgsConstructor
public class CoverLetterApiController {

    private final S3Service s3Service;

//    @Operation(summary = "자기소개서 UpLoad API",description = "자기소개서 저장을 수행한다.")
//    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> uploadCoverletterFile(@RequestParam("file") MultipartFile file) {
//        try {
//            String fileUrl = s3Service.uploadFile(file,S3Type.COVERLETTER);
//            return new ResponseEntity<>(fileUrl, HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Operation(summary = "자기소개서 DownLoad API",description = "파일이름을 입력하면 이를 기반으로 자기소개서를 내려준다.")
    @GetMapping(value="/download/{fileName}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadCoverletter(@PathVariable String fileName) {
        byte[] data = s3Service.downloadFile(fileName, S3Type.COVERLETTER);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }


}
