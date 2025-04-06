package com.example.transaction.dto;

import com.example.transaction.constants.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {
    private Long fromAccountId;
    private ReceiverAccountDetailsDto receiverAccountDetails;
    private BigDecimal amountToBeTransferred;
    private BigDecimal additionalCharges;
    private TransactionType transferMethod;
}
