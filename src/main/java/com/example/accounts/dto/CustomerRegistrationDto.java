package com.example.accounts.dto;

import com.example.accounts.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerRegistrationDto {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobile;

    @Min(value = 1, message = "Account type can be either 1 or 2")
    @Max(value = 2, message = "Account type can be either 1 or 2")
    private int accountType;

    @NotBlank(message = "Branch address is required")
    private String branchAddress;

    @NotBlank(message = "IFSC code is required")
    private String ifscCode;

    private String createdBy;

    @Valid
    @NotEmpty(message = "Address is required")
    private List<AddressRequestDto> addresses;
}
