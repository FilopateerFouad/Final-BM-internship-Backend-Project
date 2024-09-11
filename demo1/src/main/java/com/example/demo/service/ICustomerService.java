package com.example.demo.service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.UpdateCustomerDTO;
import com.example.demo.DTO.UpdatePasswordDTO;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.WrongPasswordException;

public interface ICustomerService {
    CustomerDTO getCustomerbyId(long customerId)throws ResourceNotFoundException;
    CustomerDTO updateCustomerData(Long CustomerId,UpdateCustomerDTO customerDTO)throws ResourceAlreadyExistException, ResourceNotFoundException;
    CustomerDTO updateCustomerPassword(Long CustomerId, UpdatePasswordDTO customerPasswordDTO)throws  ResourceNotFoundException, WrongPasswordException;
}
