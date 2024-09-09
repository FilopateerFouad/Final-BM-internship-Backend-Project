package com.example.demo.DTO;

import com.example.demo.DTO.enums.AccountCurrency;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TransactionDTO {
    private Long id;
    private AccountDTO sender;
    private AccountDTO recipient;
    private Double amount;
    private LocalDateTime createdAt;
    private Boolean success;
    private AccountCurrency currency;
}
