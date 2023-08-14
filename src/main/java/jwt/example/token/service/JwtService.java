package jwt.example.token.service;

import jwt.example.token.dto.LoginRequest;
import jwt.example.token.dto.LoginResponse;
import jwt.example.token.entity.Role;
import jwt.example.token.entity.User;
import jwt.example.token.repo.UserRepositry;
import jwt.example.token.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
@Autowired
private AuthenticationManager manager;
@Autowired
private JwtUtil util;
    @Autowired
    private UserRepositry userRepositry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepositry.findById(username).get();
        if (user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)

            );

        }else {
            throw new UsernameNotFoundException("not found");

        }
    }
    private Set getAuthority(User user){

        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        for (Role role: user.getRole()) {
            authorities.add(new SimpleGrantedAuthority("ROLE"+role.getName()));

        }
        return authorities;
    }

    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception {
String username=loginRequest.getUserName();
String userPassword=loginRequest.getPassword();
authenticate(username,userPassword);
UserDetails userDetails=loadUserByUsername(username);
        System.out.println("user details"+userDetails);
String newGeneratedToken=util.generateToken(userDetails);
        System.out.println("token"+newGeneratedToken);
User user=userRepositry.findById(username).get();
LoginResponse loginResponse =new LoginResponse(
 user,newGeneratedToken

);
return loginResponse;
    }
    private void authenticate(String userName,String userPassword)throws Exception{
        try {
manager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));

        }catch (BadCredentialsException e){
            throw new Exception("user invalid");

        }

    }
}
