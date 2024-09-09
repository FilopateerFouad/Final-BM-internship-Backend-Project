package com.example.demo.service;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.CreateAccountDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Override
    @Transactional
    public AccountDTO createAccount( CreateAccountDTO accountDTO)throws ResourceNotFoundException {
        Customer customer=this.customerRepository.findById(accountDTO.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Account with Id " + accountDTO.getCustomerId() + " is not found"));
        Account account= Account.builder()
                .accountName(accountDTO.getAccountName())
                .balance(0.0)
                .accountType(accountDTO.getAccountType())
                .accountDescription(accountDTO.getAccountDescription())
                .currency(accountDTO.getCurrency())
                .accountNumber(new SecureRandom().nextInt(10000000)+"")
                .customer(customer)
                .build();
        Account savedAccount = this.accountRepository.save(account);
        return savedAccount.toDTO();
    }
    @Override
    public AccountDTO updateAccount(Long customerId, AccountDTO accountDTO) {
        return null;
    }
    @Override
    public void deleteAccount(Long accountId) {
    }
    @Override
    public void deposit(Long accountId, Double amount) {

    }

    @Override
    public AccountDTO getAccountById(Long accountId) throws ResourceNotFoundException{
        return this.accountRepository.findById(accountId)
                .orElseThrow(()->new ResourceNotFoundException("Account with Id " + accountId + " is not found")).toDTO();
    }

    @Override
    public Set<TransactionDTO> getAllTransactions(Long accountId) throws ResourceNotFoundException {
        Account account=this.accountRepository.findById(accountId).orElseThrow(()->new ResourceNotFoundException("Account with Id " + accountId + " is not found"));
     return account.getTransactions().stream().map(Transaction::toDTO).sorted(Comparator.comparing(TransactionDTO::getCreatedAt).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Double getBalanaceById(Long accountId) throws ResourceNotFoundException {
        Account account=this.accountRepository.findById(accountId).orElseThrow(()->new ResourceNotFoundException("Account with Id " + accountId + " is not found"));
        return account.getBalance();
    }
}
