package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginResponse {
    private Long customerId;
    private String token;
    private String tokenType;
    private String message;
    private HttpStatus status;
    private AccountDTO mainAccount;
}