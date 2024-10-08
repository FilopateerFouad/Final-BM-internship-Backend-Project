package com.example.demo.service.security;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.RegisterCustomerRequest;
import com.example.demo.DTO.RegisterCustomerResponse;
import com.example.demo.DTO.enums.AccountCurrency;
import com.example.demo.DTO.enums.AccountType;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final JwtUtils jwtUtils;

    public RegisterCustomerResponse register(RegisterCustomerRequest body) throws ResourceAlreadyExistException {
        if (Boolean.TRUE.equals(this.customerRepository.existsByEmail(body.getEmail()))) {
            throw new ResourceAlreadyExistException("Customer with email " + body.getEmail() + " already exists");
        }
        Customer customer = Customer.builder()
                .email(body.getEmail())
                .password(this.passwordEncoder.encode(body.getPassword()))
                .name(body.getName())
                .DateOfBirth(body.getDateOfBirth())
                .Country(body.getCountry())
                .build();

        Account account = Account.builder()
                .balance(BigDecimal.valueOf(1000.0))
                .accountType(AccountType.SAVINGS)
                .accountName(body.getName())
                .accountDescription("Saving Account")
                .currency(AccountCurrency.EGP)
                .accountNumber(new SecureRandom().nextInt(10000000) + "")
                .customer(customer)
                .mainCustomer(customer)
                .build();
        customer.getAccounts().add(account);
        customer.setMainAccount(account);
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.toResponse();
    }

    @Override
    public LoginResponse Login(LoginRequest LoginRequest) throws ResourceNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(LoginRequest.getEmail(), LoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        Customer customer = this.customerRepository.findUserByEmail(LoginRequest.getEmail()).
                orElseThrow(()->new ResourceNotFoundException("Customer with email " + LoginRequest.getEmail() + " notfound"));
        return LoginResponse.builder()
                .token(jwt)
                .message("Login Successful")
                .status(HttpStatus.ACCEPTED)
                .tokenType("Bearer")
                .mainAccount(customer.getMainAccount().toDTO())
                .customerId(customer.getId())
                .build();
    }
    public void logout(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth == null || !headerAuth.startsWith("Bearer ")) {
            return;
        }
        String jwt = headerAuth.substring(7);
        Token storedToken = tokenRepository.findByToken(jwt).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
        SecurityContextHolder.clearContext();
    }
    }