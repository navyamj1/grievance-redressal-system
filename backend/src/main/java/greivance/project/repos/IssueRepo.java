package greivance.project.repos;

import greivance.project.entity.Issue;
import greivance.project.entity.enums.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepo extends JpaRepository<Issue,Long> {
    List<Issue> findIssueByStatus(IssueStatus status);
    List<Issue> findIssueByDepartmentId(Long departmentId);
    List<Issue> findIssueByUserId(Long userId);
    List<Issue> findIssueByDepartmentIdAndStatus(Long departmentId, IssueStatus status);
}
