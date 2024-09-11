package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
        Account findByAccountNumber(String accountNumber)throws ResourceNotFoundException;
}
