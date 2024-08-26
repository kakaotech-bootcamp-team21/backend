package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Notification;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.User;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.ChattingRoom;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.enums.NoticeType;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;

    @Transactional
    public Notification createNotification(User user, ChattingRoom room, String content, NoticeType type) {
        Notification notification = new Notification(LocalDateTime.now(), content, false, type);
        notification.addUser(user);
        if (room != null) {
            notification.addChattingRoom(room);
        }
        return notificationRepo.save(notification);
    }

    public Optional<Notification> getNotification(Long notificationId) {
        return notificationRepo.findById(notificationId);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notification ID"));
        notification.setIsRead(true);
    }

    @Transactional
    public void deleteNotification(Long notificationId) {
        notificationRepo.deleteById(notificationId);
    }

    // 테스트용 메서드
    public String testNotificationService() {
        return "Notification Service is working correctly!";
    }
}

//유저 관련 기능을 나중에 추가해야함.