package jwt.example.token.cotroller;

import jwt.example.token.dto.CustomerResponse;
import jwt.example.token.dto.LoginRequest;
import jwt.example.token.dto.LoginResponse;
import jwt.example.token.entity.Customer;
import jwt.example.token.repo.CustomerRepo;
import jwt.example.token.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerControlller {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;
    @PostMapping("/save")
    public String saveCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return "saved";
    }
    @PostMapping("/authentication")
    public CustomerResponse createjwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{
        System.out.println(loginRequest);
        return customerService.createJwtToken(loginRequest);


    }

}
