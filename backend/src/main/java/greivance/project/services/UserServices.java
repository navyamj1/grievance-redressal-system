package greivance.project.services;

import greivance.project.entity.User;
import greivance.project.entity.enums.Role;
import greivance.project.repos.UserRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServices {

    private final UserRepo repo;

    public UserServices(UserRepo repo) {
        this.repo = repo;
    }

    public Iterable<User> GetAllUsers() {
        return repo.findAll();
    }

    public User postUser(String username) {

        User existingUser = repo.findByUsername(username);

        if (existingUser != null) {
            return existingUser;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword("temp123"); // Temporary password
        user.setRole(Role.USER);

        repo.save(user);

        return user;
    }
    public Optional<User> getUserById(Long id){
        return repo.findById(id);
    }

}