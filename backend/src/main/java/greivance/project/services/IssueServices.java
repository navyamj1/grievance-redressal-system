package greivance.project.services;

import greivance.project.entity.Issue;
import greivance.project.entity.User;
import greivance.project.entity.enums.IssueStatus;
import greivance.project.repos.IssueRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class IssueServices {
    private final IssueRepo repo;

    public IssueServices(IssueRepo repo){
        this.repo = repo;
    }

    public Iterable<Issue> GetAllIssues(){
        return repo.findAll();
    }

    public String PostIssue(
             String username,
             String title,
             String description,
             String imageUrl,
             Double latitude,
             Double longitude
    ){
        User newUser = new User();
        newUser.setName(username);
        Issue newIssue = new Issue();
        newIssue.setUser(newUser);
        newIssue.setDescription(description);
        newIssue.setStatus(IssueStatus.OPEN);
        newIssue.setTitle(title);
        newIssue.setLongitude(longitude);
        newIssue.setLatitude(latitude);
        if(imageUrl.equals("/")){
            newIssue.setImageUrl(null);
        }else{
            newIssue.setImageUrl(imageUrl);
        }
        repo.save(newIssue);
        return "Success Yippee";

    }
}
