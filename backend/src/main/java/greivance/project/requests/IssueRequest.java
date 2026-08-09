package greivance.project.requests;

import greivance.project.entity.enums.IssueStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class IssueRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    @NotNull
    private IssueStatus status = IssueStatus.OPEN;

    private String imageUrl;
    @NotNull
    private Long userId;
    @NotNull
    private Long departmentId;
}
