package com.example.cards.dto;

import com.example.cards.constant.CardNetworkType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCreditCardDto {
    @NotBlank(message = "Card name is required")
    private String cardName;

    @NotNull(message = "Card Network type can't be blank")
    private CardNetworkType cardNetworkType;

    @Min(value = 0, message = "Card issuance charges can't be negative")
    private BigDecimal joiningCharges;

    @Min(value = 0, message = "Yearly charges can't be negative")
    private BigDecimal yearlyCharges;

    @NotNull
    private LocalDateTime effectiveFrom;

    @NotNull
    private LocalDateTime effectiveTo;
}
