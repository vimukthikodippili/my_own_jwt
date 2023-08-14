package jwt.example.token.repo;

import jwt.example.token.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;



@Repository
@EnableJpaRepositories
public interface RoleRepositry extends JpaRepository<Role,String> {
}
