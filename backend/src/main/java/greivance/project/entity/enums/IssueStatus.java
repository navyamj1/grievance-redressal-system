package greivance.project.entity.enums;

public enum IssueStatus {
    // citizen has filed the issue, no department action yet
    SUBMITTED,
    // department has acknowledged the issue
    RECEIVED,
    // officer is assessing the issue
    UNDER_REVIEW,
    // work has started
    IN_PROGRESS,
    // work is done, awaiting closure
    RESOLVED,
    // terminal, no further updates
    CLOSED,
    // terminal, rejected with reason
    REJECTED
}
