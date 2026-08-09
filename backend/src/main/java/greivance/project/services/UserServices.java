package greivance.project.services;

import greivance.project.entity.User;
import greivance.project.entity.enums.Role;
import greivance.project.exceptions.UserNotFoundException;
import greivance.project.repos.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode("temp123")); // Temporary password
        user.setRole(Role.CITIZEN);

        repo.save(user);

        return user;
    }

    public Optional<User> getUserById(Long id){
        return repo.findById(id);
    }

    public User getByUsername(String username) {
        User user = repo.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        return user;
    }

    /** Promotes or demotes a user, the only way to create an OFFICIAL or ADMIN. */
    public User updateRole(Long id, Role role) {
        User user = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setRole(role);

        return repo.save(user);
    }
}
