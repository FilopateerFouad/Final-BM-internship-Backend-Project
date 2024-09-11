package com.example.demo.service;
import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.CreateAccountDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IAccountService {

    /**
     * Create a new account
     *
     * @param accountDTO the account to be created
     * @return the created account
     * @throws ResourceNotFoundException if the account is not found
     */
    AccountDTO createAccount(CreateAccountDTO accountDTO) throws ResourceNotFoundException;

    /**
     * Get account by id
     *
     * @param accountNumber the account id
     * @return the account
     * @throws ResourceNotFoundException if the account is not found
     */
    AccountDTO getAccountById(String accountNumber) throws ResourceNotFoundException;
    List<TransactionDTO> getAllTransactions(String accountNumber)throws ResourceNotFoundException;
    BigDecimal getBalanaceById(String accountNumber)throws ResourceNotFoundException;

    void transferMoney(String fromAccount, String toAccount, BigDecimal amount)throws ResourceNotFoundException;


}