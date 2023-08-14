package jwt.example.token.cotroller;


import jwt.example.token.entity.User;
import jwt.example.token.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    // Endpoint for registering a new user
    @PostMapping("/register-new-user")
    public User registerUser(@RequestBody User user){
        return userService.registerNewUser(user);


    }
    // Method annotated with @PostConstruct will be executed once the Spring application context is fully initialized.
    // It is used to initialize roles and users in the system.
//
//    @PostConstruct
//    public void initRoleAndUser(){
//        userService.initRoleUser();
//
//    }
}
