package com.example.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPayeeDetailsDto {

    @Digits(integer = 18, fraction = 0, message = "Account number must be between 9 to 18 digits")
    @Min(100000000L)
    @Max(999999999999999999L)
    private Long accountTobeAdded;

    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;

    @NotBlank(message = "IFSC code is required")
    private String ifscCode;

    @NotNull(message = "Mention if bank same as sender")
    private boolean bankSameAsSender;
}
