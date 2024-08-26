package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification", description = "알림 관련 API")
public class NotificationApiController {

    @GetMapping("/test")
    @Operation(summary = "알림 API 테스트", description = "알림 API가 정상 작동하는지 테스트합니다.")
    public NotificationTestResponse notificationTest() {
        return new NotificationTestResponse("Notification test successful!");
    }

    @Data
    @AllArgsConstructor
    static class NotificationTestResponse {
        private String message;
    }
}