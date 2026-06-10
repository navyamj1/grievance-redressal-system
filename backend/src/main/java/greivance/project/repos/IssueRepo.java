package greivance.project.repos;

import greivance.project.entity.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends CrudRepository<Issue,Long> {
}
