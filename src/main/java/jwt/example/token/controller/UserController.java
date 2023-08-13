package jwt.example.token.controller;

import jwt.example.token.User;
import jwt.example.token.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin

public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        return userService.saveUser(user);


    }

}
