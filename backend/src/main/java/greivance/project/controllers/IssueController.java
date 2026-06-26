package greivance.project.controllers;

import greivance.project.responses.IssueResponse;
import greivance.project.services.IssueServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import greivance.project.requests.IssueRequest;

import java.util.List;


@RestController
@RequestMapping("/issues")
public class IssueController {

    //Testing the apu need to make changes when docker is set

    private final IssueServices issueServices;

    public IssueController(IssueServices service){
        issueServices= service;
    }

    @GetMapping
    public List<IssueResponse> getIssues(){
        return issueServices.getAllIssues();
    }

    @PostMapping
    public ResponseEntity<IssueResponse> postIssue(
         @Valid @RequestBody IssueRequest request
    ){
        IssueResponse issue = issueServices.postIssue(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> getIssueById(@PathVariable Long id){
        return ResponseEntity.ok(issueServices.getIssueById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueResponse> updateIssue(
            @PathVariable Long id,
            @Valid @RequestBody IssueRequest request)
    {
        return ResponseEntity.ok(issueServices.updateIssue(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id){
        issueServices.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }

}
