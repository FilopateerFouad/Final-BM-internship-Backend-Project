package com.example.demo.entity;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.RegisterCustomerResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private String Country;
    @Column(nullable = false)
    private LocalDate DateOfBirth;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Account> accounts = new HashSet<>();
    @OneToOne(mappedBy = "customer")
    private Token token;
 public RegisterCustomerResponse toResponse() {
        return RegisterCustomerResponse.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .Country(this.Country)
                .DateOfBirth(this.DateOfBirth)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt).build();
    }
    public CustomerDTO toDTO() {
        return CustomerDTO.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .accounts(this.accounts.stream().map(Account::toDTO).collect(Collectors.toSet()))
                .build();
    }
}