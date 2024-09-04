package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.S3Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
public class S3Service { //AWS S3 버킷에 S3Type을 이용하여 다양한 파일을 저장한다.

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    private final String bucketName;

    private final String region;

    public S3Service(@Value("${aws.s3.bucketName}") String bucketName,
                     @Value("${aws.region}") String region,
                     @Value("${aws.accessKeyId}") String accessKeyId,
                     @Value("${aws.secretAccessKey}") String secretAccessKey) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();
        this.s3Presigner = S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();
        this.bucketName = bucketName;
        this.region = region;
    }

    // S3에 파일 업로드
    public String uploadFile(MultipartFile file, S3Type s3Type) throws IOException {
        // 파일의 확장자 추출
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 고유한 파일명 생성
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // 파일 유형에 따라 S3 키(경로) 결정
        String s3Key = determineS3Key(uniqueFileName,s3Type);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        try {
            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
        } catch (S3Exception e) {
            throw new RuntimeException("Error while uploading file to S3", e);
        }

        return getFileUrl(s3Key,s3Type);
    }

    // S3 객체 삭제 메서드
    public void deleteFile(String fileName,S3Type s3Type) {
        try {
            String s3Key = determineS3Key(fileName,s3Type);

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
        } catch (S3Exception e) {
            throw new RuntimeException("Error while deleting file from S3", e);
        }
    }

    // 업로드된 파일의 URL 가져오기 (+ 특정 시간 동안 url을 통해 자원에 접근 할 수 있는 권한 부여.)
    public String getFileUrl(String fileName,S3Type s3Type) {

        String s3Key = determineS3Key(fileName,s3Type);
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(s3Key)
                        .build())
                .signatureDuration(Duration.ofMinutes(10))
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
        return presignedGetObjectRequest.url().toString();
    }

    // S3에서 파일 다운로드
    public byte[] downloadFile(String fileName,S3Type s3Type) {

        String s3Key = determineS3Key(fileName,s3Type);

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        try {
            return s3Client.getObjectAsBytes(getObjectRequest).asByteArray();
        } catch (S3Exception e) {
            throw new RuntimeException("Error while downloading file from S3", e);
        }
    }

    // 파일 유형에 따라 S3 키(경로)를 결정하는 메서드
    private String determineS3Key(String uniqueFileName,S3Type s3Type) {
        String prefix = "others/";

        if (s3Type != null) {
            if (s3Type==S3Type.IMAGE) {
                prefix = "images/";
            } else if (s3Type == S3Type.COVERLETTER) {
                // 자소서 파일로 간주할 수 있는 확장자
                prefix = "coverletters/";
            } else if (s3Type == S3Type.PORTFOLIO) {
                prefix= "portfolio/";
            }
        }

        return prefix + uniqueFileName;
    }

    // aws s3 객체 url에서 fileName 추출 하는 메서드.
    public String extractFileNameFromUrl(String urlString) {
        try {
            // URL 객체 생성
            URL url = new URL(urlString);

            // URL의 경로 가져오기
            String path = url.getPath();

            // 파일명 추출
            String fileName = path.substring(path.lastIndexOf("/") + 1);

            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

