package com.hari.netflix.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email address is not valid")
    String email;
    @NotBlank(message = "Password cannot be blank")
    String password;
    @NotBlank(message = "Name cannot be blank")
    String name;
    @NotBlank(message = "Contact number cannot be blank")
    @Size(min=10, max=10, message = "Contact number should be of 10 digits only")
    String mobileNumber;
    @Min(value = 13, message = "Invalid age value. Should be at least 13")
    @Max(value = 150, message = "Invalid age value. Should be at most 150")
    Integer age;
    @NotBlank(message = "Requires a gender value")
    String gender;
    @NotBlank(message = "Address cannot be blank")
    String primaryAddress;
}
