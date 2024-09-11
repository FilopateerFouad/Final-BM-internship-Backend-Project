package com.example.demo.entity;

import com.example.demo.DTO.FavouriteDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Favourite {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String recipientName;
    @Column(nullable = false)
    private String recipientAccount;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;
    public FavouriteDTO toDTO() {
        return FavouriteDTO.builder().
        recipientAccount(this.recipientAccount).
         recipientName(this.recipientName).
               build();
    }
}
