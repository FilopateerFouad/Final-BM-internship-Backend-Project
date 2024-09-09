package com.example.demo.service;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.UpdateCustomerDTO;
import com.example.demo.DTO.UpdatePasswordDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.WrongPasswordException;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDTO getCustomerbyId(long customerId) throws ResourceNotFoundException {
            return this.customerRepository.findById(customerId).
                    orElseThrow(()->new ResourceNotFoundException("Customer with id " + customerId + " notfound"))
                    .toDTO();
    }

    @Override
    public CustomerDTO updateCustomerData(Long customerId,UpdateCustomerDTO customerDTO) throws ResourceNotFoundException, CustomerAlreadyExistException {
        Customer customer = this.customerRepository.
                findById(customerId).
                orElseThrow(() -> new ResourceNotFoundException
                        ("Customer with id " + customerId + " notfound"));
        if (Boolean.TRUE.equals(this.customerRepository.existsByEmail(customerDTO.getEmail()))) {
            throw new CustomerAlreadyExistException("Customer with email " + customerDTO.getEmail() + " already exists");
        }
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCountry(customerDTO.getCountry());
        customer.setUpdatedAt(customerDTO.getUpdateDate());
        customer.setDateOfBirth(customerDTO.getDateofBirth());
        return customer.toDTO();
    }

    @Override
    public CustomerDTO updateCustomerPassword(Long CustomerId, UpdatePasswordDTO customerPasswordDTO) throws ResourceNotFoundException, WrongPasswordException {
        Customer customer = this.customerRepository.
                findById(CustomerId).
                orElseThrow(() -> new ResourceNotFoundException
                        ("Customer with id " + CustomerId + " notfound"));
        if(passwordEncoder.matches(customerPasswordDTO.getOldPassword(), customer.getPassword()))
            customer.setPassword(passwordEncoder.encode( customerPasswordDTO.getNewPassword()));
        else
            throw new WrongPasswordException("Wrong password");
        return customer.toDTO();

    }

}
