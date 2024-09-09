package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UpdateCustomerDTO {
    private int customerId;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String Country;
    @NotNull
    private LocalDate DateofBirth;
    @NotNull
    private LocalDateTime updateDate;
}
