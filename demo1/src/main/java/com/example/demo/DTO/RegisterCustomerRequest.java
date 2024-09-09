package com.example.demo.DTO;
import com.example.demo.validation.confirmpass.PasswordConfirmation;
import com.example.demo.validation.ageValidator.ValidAge;
import com.example.demo.validation.countryValidator.ValidCountryName;
import com.example.demo.validation.emailValidor.ValidEmail;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@PasswordConfirmation(message = "Password doesn't match")
public class RegisterCustomerRequest {
    @NotBlank(message = "You must enter your name")
    private String name;
    @Email(message = "You must enter your email in correct format ")
    @NotBlank(message = "You must enter your email")
    @ValidEmail(message = "enter the correct email")
    private String email;
    @NotBlank
    @Size(min = 6,message = "Enter your password correctly.minimum size 6 and maximum size 20", max = 20)
    private String password;
    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @ValidAge(min = 21, message = "You must be at least 21 years old")
    private LocalDate dateOfBirth;
    @NotBlank(message = "You must enter your country")
    @ValidCountryName(message = "Please enter the Selected country")
    private String Country;
}
