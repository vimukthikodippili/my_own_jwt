package jwt.example.token.service;

import jwt.example.token.dto.CustomerResponse;
import jwt.example.token.dto.LoginRequest;
import jwt.example.token.dto.LoginResponse;
import jwt.example.token.entity.Customer;

import jwt.example.token.entity.Role;
import jwt.example.token.repo.CustomerRepo;
import jwt.example.token.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtUtil util;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
private PasswordEncoder passwordEncoder;
    public void saveCustomer(Customer customer) {

 customer.setPassword(passwordEncoder.encode(customer.getPassword()));
     customerRepo.save(customer);
    }


    public CustomerResponse createJwtToken(LoginRequest loginRequest) throws Exception {
String custname=loginRequest.getUserName();
String custpassword=loginRequest.getPassword();
UserDetails userDetails=loadUserByUsername(custname);
        String newToken=util.generateToken(userDetails);
        Customer customer=customerRepo.findById(custname).get();
        CustomerResponse customerResponse=new CustomerResponse(

                customer,newToken
        );
        return customerResponse;
//        authenticate(username,userPassword);
//        UserDetails userDetails=loadUserByUsername(username);
//        System.out.println("user details"+userDetails);
//        String newGeneratedToken=util.generateToken(userDetails);
//        System.out.println("token"+newGeneratedToken);
//        Customer customer =customerRepo.findById(username).get();
//        CustomerResponse loginResponse =new LoginResponse(
//                customer,newGeneratedToken
//
//        );
//        return loginResponse;
    }
    private void authenticate(String userName,String userPassword)throws Exception{
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));

        }catch (BadCredentialsException e){
            throw new Exception("user invalid");

        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Customer customer=customerRepo.findById(username).get();
//        if (customer!=null){
//            return new org.springframework.security.core.userdetails.User(
//                    customer.getName(),
//                    customer.getPassword(),
//                    getAuthority(customer)
//
//            );
//
//        }else {
//            throw new UsernameNotFoundException("not found");
//
//        }
        return User.builder()
                .username(customer.getName())
                .password(customer.getPassword())
                .authorities(getAuthorities(customer))
                .build();
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Customer customer) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Assuming you have a getRoles() method in your Customer class that returns a List<Role>
        String[] roles = customer.getName().split(",");
        for (String roleName : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName.trim()));
        }

        return authorities;
//    private Set getAuthority(Customer customer){
//
//        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
//
//        return authorities;
//    }
    }
}
