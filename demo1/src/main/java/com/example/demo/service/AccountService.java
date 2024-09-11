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

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
                .balance(BigDecimal.valueOf(1000.0))
                .accountType(accountDTO.getAccountType())
                .accountDescription(accountDTO.getAccountDescription())
                .currency(accountDTO.getCurrency())
                .accountNumber(new SecureRandom().nextInt(10000000)+"")
                .customer(customer)
                .build();
        Account savedAccount = this.accountRepository.save(account);
        customer.getAccounts().add(savedAccount);
        this.customerRepository.save(customer);
        return savedAccount.toDTO();
    }
    @Override
    public AccountDTO getAccountById(String accountNumber) throws ResourceNotFoundException{
        Account account=accountRepository.findByAccountNumber(accountNumber);
        if(account==null){
            throw new ResourceNotFoundException("Account with Id " + accountNumber + " is not found");
        }
        return account.toDTO();
    }

    @Override
    public List<TransactionDTO> getAllTransactions(String accountNumber) throws ResourceNotFoundException {
        Account account=this.accountRepository.findByAccountNumber(accountNumber);
    if(account==null){
    throw new ResourceNotFoundException("Account not found");
}
     return account.getTransactions().stream().map(Transaction::toDTO).sorted(Comparator.comparing(TransactionDTO::getCreatedAt).reversed()).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getBalanaceById(String accountNumber) throws ResourceNotFoundException {
        Account account=this.accountRepository.findByAccountNumber(accountNumber);
        if(account==null){
          throw new ResourceNotFoundException("Account with Id " + accountNumber + " is not found");
        }
        return account.getBalance();
    }
    @Transactional
    public void transferMoney(String fromAccount, String toAccount, BigDecimal amount)throws ResourceNotFoundException {
        Account sourceAccount = accountRepository.findByAccountNumber(fromAccount);
        Account targetAccount = accountRepository.findByAccountNumber(toAccount);

        if (sourceAccount == null || targetAccount == null) {
            throw new ResourceNotFoundException("Current Account not found");
        }

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        Transaction Sendertransaction=Transaction.builder()
                .senderAccount(sourceAccount.getAccountNumber())
                .senderName(sourceAccount.getAccountName())
                .recipientAccount(targetAccount.getAccountNumber())
                .recipientName(targetAccount.getAccountName())
                .success(true)
                .amount(amount)
                .currency(sourceAccount.getCurrency())
                .createdAt(LocalDateTime.now())
                .account(sourceAccount)
                .build();
        Transaction Recipienttransaction=Transaction.builder()
                .senderAccount(sourceAccount.getAccountNumber())
                .senderName(sourceAccount.getAccountName())
                .recipientAccount(targetAccount.getAccountNumber())
                .recipientName(targetAccount.getAccountName())
                .success(true)
                .amount(amount)
                .currency(sourceAccount.getCurrency())
                .createdAt(LocalDateTime.now())
                .account(targetAccount)
                .build();
        sourceAccount.getTransactions().add(Sendertransaction);
        targetAccount.getTransactions().add(Recipienttransaction);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }
}
