package jwt.example.token.dto;



import jwt.example.token.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private User user;
    private String jwtToken;
}
