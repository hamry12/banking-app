package com.example.transaction.dto;

import com.example.transaction.constants.TransfferMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {
    private String fromAccountId;
    private ReceiverAccountDetailsDto receiverAccountDetails;
    private BigDecimal amountToBeTransferred;
    private BigDecimal additionalCharges;
    private TransfferMethod transferMethod;
}
