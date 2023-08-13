package jwt.example.token.repo;

import jwt.example.token.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
