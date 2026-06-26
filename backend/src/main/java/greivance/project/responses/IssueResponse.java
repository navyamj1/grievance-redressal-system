package greivance.project.responses;

import greivance.project.entity.Issue;
import greivance.project.entity.enums.IssueStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueResponse {
    private Long id;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
    private IssueStatus status;
    private Long userId;
    private String userName;
    private Long departmentId;
    private String departmentName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
