package greivance.project.services;

import greivance.project.entity.Department;
import greivance.project.entity.Issue;
import greivance.project.entity.User;
import greivance.project.entity.enums.IssueStatus;
import greivance.project.exceptions.DepartmentNotFoundException;
import greivance.project.exceptions.IssueNotFoundException;
import greivance.project.repos.IssueRepo;
import greivance.project.responses.IssueResponse;
import org.springframework.stereotype.Service;
import greivance.project.requests.IssueRequest;
import greivance.project.exceptions.UserNotFoundException;

import java.util.List;

@Service
public class IssueServices {
    private final IssueRepo repo;

    private final UserServices userServices;
    private final DepartmentServices departmentServices;

    public IssueServices(IssueRepo repo, UserServices userServices, DepartmentServices departmentServices){
        this.repo = repo;
        this.userServices = userServices;
        this.departmentServices = departmentServices;
    }

    public List<IssueResponse> getAllIssues(){
        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public IssueResponse postIssue(
        IssueRequest request
    ){
        User user =  findUser(request.getUserId());
        Department department= findDepartment(request.getDepartmentId());
        Issue issue = buildIssue(request,user,department);

        Issue savedIssue = repo.save(issue);

        return toResponse(savedIssue);
    }
    public IssueResponse getIssueById(Long id){
        Issue issue= repo.findById(id).orElseThrow(()-> new IssueNotFoundException(id));
        return toResponse(issue);
    }
    public IssueResponse updateIssue(Long id,IssueRequest request){
        Issue issue = repo.findById(id).orElseThrow(()->new IssueNotFoundException(id));
        User user = findUser(request.getUserId());
        Department department = findDepartment(request.getDepartmentId());
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setLongitude(request.getLongitude());
        issue.setLatitude(request.getLatitude());
        issue.setUser(user);
        issue.setImageUrl(normalizeImageUrl(request.getImageUrl()));
        issue.setStatus(request.getStatus());
        issue.setDepartment(department);

        Issue savedIssue = repo.save(issue);
        return toResponse(savedIssue);
    }

    public void deleteIssue(Long id){
        Issue issue = repo.findById(id).orElseThrow(()->new IssueNotFoundException(id));

        repo.delete(issue);
    }

    public List<IssueResponse> getIssuesByUserId(Long id){
        return  repo.findIssueByUserId(id)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public List<IssueResponse> getIssuesByDepartmentId(Long id){
        return repo.findIssueByDepartmentId(id)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public List<IssueResponse> getIssuesByStatus(IssueStatus status){
        return repo.findIssueByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public List<IssueResponse> getIssuesByDepartmentAndStatus(Long id,IssueStatus status){
        return  repo.findIssueByDepartmentIdAndStatus(id,status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private User findUser(Long userid) {
        return userServices.getUserById(userid).orElseThrow(()->new UserNotFoundException(userid));
    }
    private Department findDepartment(Long departmentId){
        return departmentServices.getDepartmentById(departmentId).orElseThrow(()-> new DepartmentNotFoundException(departmentId));
    }

    private Issue buildIssue(IssueRequest request,User user,Department department){
        return Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .imageUrl(normalizeImageUrl(request.getImageUrl()))
                .status(IssueStatus.OPEN)
                .user(user)
                .department(department)
                .build();
    }
    private String normalizeImageUrl(String imageUrl){
        return (imageUrl==null||imageUrl.isBlank())?null : imageUrl;
    }
    private IssueResponse toResponse(Issue issue) {
        return IssueResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .imageUrl(issue.getImageUrl())
                .latitude(issue.getLatitude())
                .longitude(issue.getLongitude())
                .status(issue.getStatus())
                .userId(issue.getUser().getId())
                .userName(issue.getUser().getUsername())
                .departmentId(issue.getDepartment().getId())
                .departmentName(issue.getDepartment().getName())
                .createdAt(issue.getCreatedAt())
                .updatedAt(issue.getUpdatedAt())
                .build();
    }

}
