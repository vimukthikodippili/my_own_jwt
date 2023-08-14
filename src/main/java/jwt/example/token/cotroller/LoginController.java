package jwt.example.token.cotroller;



import jwt.example.token.dto.LoginRequest;
import jwt.example.token.dto.LoginResponse;
import jwt.example.token.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private JwtService jwtService;
    // Endpoint for user authentication and token generation
    @PostMapping("/authentication")
    public LoginResponse createjwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{

         return jwtService.createJwtToken(loginRequest);


    }
}
