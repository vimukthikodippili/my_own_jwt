package jwt.example.token.service;



import jwt.example.token.entity.Role;
import jwt.example.token.repo.RoleRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class RoleService {
    @Autowired
    private RoleRepositry roleRepo;
    public Role createRole(Role role){
        return roleRepo.save(role);

    }

}
