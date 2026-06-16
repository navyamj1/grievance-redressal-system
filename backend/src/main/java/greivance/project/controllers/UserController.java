package greivance.project.controllers;

import greivance.project.entity.User;
import greivance.project.services.UserServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return userServices.AddUserAndGet(username);
    }

}