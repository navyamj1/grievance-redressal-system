package greivance.project.controllers;

import greivance.project.entity.Issue;
import greivance.project.entity.enums.IssueStatus;
import greivance.project.entity.User;
import greivance.project.repos.IssueRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IssueController {

    //Testing the apu need to make changes when docker is set

    private final IssueRepo repo;

    private List<Issue> issues = new ArrayList<>();

    public IssueController(IssueRepo repo){
        this.repo = repo;
    }

    @GetMapping("/issue")
    public @ResponseBody Iterable<Issue> getIssues(){
        return issues;
    }

    @PostMapping("/post-issues")
    public @ResponseBody String postIssue(
            @RequestParam String username,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam (defaultValue = "/") String imageUrl,
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ){
        User newUser = new User();
        newUser.setName(username);
        Issue newIssue = new Issue();
        newIssue.setUser(newUser);
        newIssue.setTitle(title);
        newIssue.setDescription(description);
        if(imageUrl.equals("/")){
            newIssue.setImageUrl(null);
        }else{
            newIssue.setImageUrl(imageUrl);
        }
        newIssue.setLatitude(latitude);
        newIssue.setLongitude(longitude);
        newIssue.setStatus(IssueStatus.ASSIGNED);

        issues.add(newIssue);

        return "Saved";
    }

}
