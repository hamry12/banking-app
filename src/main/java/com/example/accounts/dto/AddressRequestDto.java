package com.example.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

    @NotBlank(message = "First line of address is required")
    private String firstLine;
    private String secondLine;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(regexp = "^[a-zA-Z]{2}$", message = "State must contain only alphabets of size 2")
    private String state;

    @NotBlank(message = "Country is required")
    @Pattern(regexp = "^[a-zA-Z]{2}$", message = "Country must contain only alphabets of size 2")
    private String country;

    @NotBlank(message = "Zip is required")
    @Size(min = 5, max = 8, message = "Value must be between 5 to 8 character")
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "Zip must contain only alphanumeric value")
    private String zip;

    @Min(value = 1, message = "Address type can be either 1 or 2")
    @Max(value = 2, message = "Address type can be either 1 or 2")
    private int addressType;
}
