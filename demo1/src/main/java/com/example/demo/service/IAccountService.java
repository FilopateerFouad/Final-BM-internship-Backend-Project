package com.example.demo.service;
import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.CreateAccountDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.exception.ResourceNotFoundException;

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
     * @param accountId the account id
     * @return the account
     * @throws ResourceNotFoundException if the account is not found
     */
    AccountDTO getAccountById(Long accountId) throws ResourceNotFoundException;
    Set<TransactionDTO> getAllTransactions(Long accountId)throws ResourceNotFoundException;
    Double getBalanaceById(Long accountId)throws ResourceNotFoundException;
    AccountDTO updateAccount(Long accountId, AccountDTO accountDTO);

    void deleteAccount(Long accountId);

    void deposit(Long accountId, Double amount);


}