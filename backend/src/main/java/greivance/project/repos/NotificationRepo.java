package greivance.project.repos;

import greivance.project.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    
    
    List<Notification> findByUser_IdOrderByCreatedAtDesc(Long userId);
    
   
    List<Notification> findByUser_IdAndIsReadFalse(Long userId);
}