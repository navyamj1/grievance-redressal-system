package greivance.project.controllers;

import greivance.project.entity.Issue;

import greivance.project.responses.IssueResponse;
import greivance.project.services.IssueServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import greivance.project.requests.IssueRequest;


@RestController
@RequestMapping("/issues")
public class IssueController {

    //Testing the apu need to make changes when docker is set

    private final IssueServices issueServices;

    public IssueController(IssueServices service){
        issueServices= service;
    }

    @GetMapping
    public Iterable<Issue> getIssues(){
        return issueServices.getAllIssues();
    }

    @PostMapping
    public ResponseEntity<IssueResponse> postIssue(
         @Valid @RequestBody IssueRequest request
    ){
        IssueResponse issue = issueServices.postIssue(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

}
