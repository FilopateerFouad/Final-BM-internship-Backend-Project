package com.example.demo.DTO;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransferRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
