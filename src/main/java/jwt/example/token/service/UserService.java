package jwt.example.token.service;

import jwt.example.token.User;
import jwt.example.token.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public User saveUser(User user) {
        System.out.println("servise"+user);
        return userRepo.save(user);


    }
}
