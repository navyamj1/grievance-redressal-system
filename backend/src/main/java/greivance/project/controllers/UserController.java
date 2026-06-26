package greivance.project.controllers;

import greivance.project.entity.User;
import greivance.project.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getUsers() {
        return userServices.GetAllUsers();
    }

    @PostMapping("/add-user")
    public @ResponseBody User addUser(
            @RequestParam String username
    ) {
        return userServices.postUser(username);
    }
    @GetMapping("/add-user/{id}")
    public ResponseEntity<User> getUserWithId(@PathVariable Long id){
        return userServices.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}