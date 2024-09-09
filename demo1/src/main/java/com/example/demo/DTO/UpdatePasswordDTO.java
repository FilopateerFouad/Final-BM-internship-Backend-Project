package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UpdatePasswordDTO {
    @NotBlank
    @Size(min = 6,message = "Enter your password correctly.minimum size 6 and maximum size 20", max = 20)
    private String oldPassword;
    @NotBlank
    @Size(min = 6,message = "Enter your password correctly.minimum size 6 and maximum size 20", max = 20)
    private String newPassword;
}
