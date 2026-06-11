package greivance.project.services;

import greivance.project.entity.User;
import greivance.project.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepo repo;

    public UserServices(UserRepo repo){
        this.repo = repo;
    }

    public User AddUserAndGet(String username){
        User user = new User();
        user.setName(username);
        repo.save(user);
        return user;
    }

}
