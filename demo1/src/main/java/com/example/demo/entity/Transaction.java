package com.example.demo.entity;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.DTO.enums.AccountCurrency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String  senderAccount;
    @Column(nullable = false)
    private String  senderName;
    @Column(nullable = false)
    private String  recipientAccount;
    @Column(nullable = false)
    private String  recipientName;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountCurrency currency;
    @Builder.Default
    @Column(nullable = false)
    private Boolean success=false;
    @Column(nullable = false)
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;
    public TransactionDTO toDTO() {
        return TransactionDTO.builder()
                .senderAccount(this.senderAccount)
                .recipientAccount(this.recipientAccount)
                .recipientName(this.recipientName)
                .senderName(this.senderName)
                .success(this.success)
                .amount(this.amount)
                .currency(this.currency)
                .createdAt(this.createdAt)
                .build();
    }
}
