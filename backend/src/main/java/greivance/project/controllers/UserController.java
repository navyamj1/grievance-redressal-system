package greivance.project.controllers;

import greivance.project.entity.enums.Role;
import greivance.project.responses.UserResponse;
import greivance.project.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public @ResponseBody List<UserResponse> getUsers() {
        return StreamSupport.stream(userServices.GetAllUsers().spliterator(), false)
                .map(UserResponse::from)
                .toList();
    }

    @PostMapping("/add-user")
    public @ResponseBody UserResponse addUser(
            @RequestParam String username
    ) {
        return UserResponse.from(userServices.postUser(username));
    }

    @GetMapping("/add-user/{id}")
    public ResponseEntity<UserResponse> getUserWithId(@PathVariable Long id){
        return userServices.getUserById(id)
                .map(UserResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> updateRole(
            @PathVariable Long id,
            @RequestParam Role role
    ){
        return ResponseEntity.ok(UserResponse.from(userServices.updateRole(id, role)));
    }
}
