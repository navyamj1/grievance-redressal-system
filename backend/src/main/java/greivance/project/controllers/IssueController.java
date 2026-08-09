package greivance.project.controllers;

import greivance.project.entity.Issue;
import greivance.project.entity.enums.IssueStatus;
import greivance.project.entity.User;
import greivance.project.services.IssueServices;
import greivance.project.repos.IssueRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IssueController {

    //Testing the apu need to make changes when docker is set

    private final IssueServices issueServices;

    public IssueController(IssueServices service){
        issueServices= service;
    }

    @GetMapping("/issue")
    public @ResponseBody Iterable<Issue> getIssues(){
        return issueServices.GetAllIssues();
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
        return issueServices.PostIssue(username,title,description,imageUrl,latitude,longitude);
    }

}
