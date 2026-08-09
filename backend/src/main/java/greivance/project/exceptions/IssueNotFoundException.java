package greivance.project.exceptions;

public class IssueNotFoundException extends RuntimeException {
    public IssueNotFoundException(Long id){
        super("Issue not found with id:"+id);
    }
}
