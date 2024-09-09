package com.example.demo.service.security;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.RegisterCustomerRequest;
import com.example.demo.DTO.RegisterCustomerResponse;
import com.example.demo.exception.CustomerAlreadyExistException;
import jakarta.servlet.http.HttpServletRequest;


public interface IAuthService {
    /**
     * Register a new customer
     *
     * @param body the customer to be registered
     * @return the registered customer
     * @throws CustomerAlreadyExistException if the customer already exists
     */
    RegisterCustomerResponse register(RegisterCustomerRequest body) throws CustomerAlreadyExistException;


    /**
     * Login a customer
     *
     * @param LoginRequest login details
     * @return login response @{@link LoginResponse}
     */
   LoginResponse Login (LoginRequest LoginRequest) throws CustomerAlreadyExistException;

    void logout(HttpServletRequest request);
}