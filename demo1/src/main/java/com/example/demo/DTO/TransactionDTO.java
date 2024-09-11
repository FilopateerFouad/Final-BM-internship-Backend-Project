package com.example.demo.DTO;

import com.example.demo.DTO.enums.AccountCurrency;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TransactionDTO {
    private String senderAccount;
    private String recipientAccount;
    private String senderName;
    private String recipientName;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private Boolean success;
    private AccountCurrency currency;

}
