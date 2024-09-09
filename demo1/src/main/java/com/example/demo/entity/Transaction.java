package com.example.demo.entity;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.DTO.enums.AccountCurrency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    @OneToOne(fetch = FetchType.EAGER)
    private Account sender;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account recipient;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountCurrency currency;
    @Builder.Default
    @Column(nullable = false)
    private Boolean success=false;
    @Column(nullable = false)
    private Double amount;
    public TransactionDTO toDTO() {
        return TransactionDTO.builder()
                .id(this.id)
                .sender(this.sender.toDTO())
                .recipient(this.recipient.toDTO())
                .success(this.success)
                .amount(this.amount)
                .currency(this.currency)
                .createdAt(this.createdAt)
                .build();
    }
}
