package com.example.demo.DTO;

import com.example.demo.DTO.enums.AccountCurrency;
import com.example.demo.DTO.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private AccountCurrency currency;

    private String accountName;

    private String accountDescription;

    private Boolean active;

    private LocalDateTime createdAt;

    private  Set<TransactionDTO> transactions;

    private LocalDateTime updatedAt;
}