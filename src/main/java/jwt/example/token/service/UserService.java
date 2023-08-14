package jwt.example.token.service;



import jwt.example.token.entity.Role;
import jwt.example.token.entity.User;
import jwt.example.token.repo.RoleRepositry;
import jwt.example.token.repo.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private RoleRepositry roleRepositry;

    public User registerNewUser(User user) {
   return userRepositry.save(user);
    }
    public void initRoleUser() {
        Role adminRole = new Role();
        if (!roleRepositry.existsById("admin")) {

            adminRole.setName("admin");
            adminRole.setRolesDescription("admin role");
            roleRepositry.save(adminRole);
        }
        if (!roleRepositry.existsById("user")) {
            Role userRole = new Role();
            userRole.setName("user");
            userRole.setRolesDescription("user role");
            roleRepositry.save(adminRole);
        }
        if (!userRepositry.existsById("admin123")) {
            User user = new User();
            user.setUserName("admin123");
            user.setUserPassword("AS@1234");
            user.setUserFirstName("vimukthi");
            user.setUserLastName("ko");

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            user.setRole(adminRoles);
            userRepositry.save(user);
        }
    }
    }


