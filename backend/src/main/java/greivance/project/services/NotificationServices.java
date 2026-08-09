package greivance.project.services;

import greivance.project.entity.Issue;
import greivance.project.entity.User;
import greivance.project.entity.Notification;
import greivance.project.repos.NotificationRepo;
import greivance.project.repos.UserRepo;
import greivance.project.repos.IssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServices {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private IssueRepo issueRepository;
    @Autowired
    private NotificationRepo  notificationRepo;
    public Notification createNotification(Long userId, Long issueId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

      
        Notification notification = Notification.builder()
                .user(user)
                .issue(issue)
                .message(message)
                .isRead(false)
                .build();

        return notificationRepo.save(notification);
    }
    public List<Notification> getUserNotifications(Long userId) {
       
        return notificationRepo.findByUser_IdOrderByCreatedAtDesc(userId);

    }
    public List<Notification> getUnreadNotifications(Long userId) {
       
        return notificationRepo.findByUser_IdAndIsReadFalse(userId);
    }
    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        
        notification.setRead(true);
        return notificationRepo.save(notification);
    }
}