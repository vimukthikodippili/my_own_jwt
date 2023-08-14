package jwt.example.token.dto;

import jwt.example.token.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponse {
    private Customer customer;
    private String jwtToken;
}
