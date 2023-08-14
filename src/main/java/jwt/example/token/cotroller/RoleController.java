package jwt.example.token.cotroller;



import jwt.example.token.entity.Role;
import jwt.example.token.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    // Endpoint for creating a new role
    @Autowired
    private RoleService roleService;
    @PostMapping("/create-new-role")
    public Role createRole(@RequestBody Role role){
      return roleService.createRole(role);

    }

}
