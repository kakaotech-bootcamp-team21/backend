package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.S3Type;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
class S3ServiceTest {

    @Autowired
    private S3Service s3Service;

    @Test
    public void 파일업로드() throws Exception {
        //given
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",                       // 파라미터 이름
                "testfile.txt",               // 파일 이름
                "text/plain",                 // 파일 타입
                "This is the file content".getBytes()  // 파일 내용
        );

        //when
        String url = s3Service.uploadFile(mockFile, S3Type.COVERLETTER);
        String fileName=s3Service.extractFileNameFromUrl(url);

        try {
            byte[] bytes = s3Service.downloadFile(fileName, S3Type.COVERLETTER);

            //then -> 임시로 생성한 multipartFile과 aws s3에 넣고 가져온 객체가 같아야 한다.
            Assertions.assertThat(bytes).isEqualTo(mockFile.getBytes());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally
        {   // 공통적으로 테스트 객체를 지워줘야 한다.
            s3Service.deleteFile(fileName,S3Type.COVERLETTER);
        }
    }

}