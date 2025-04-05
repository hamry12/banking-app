package com.example.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverAccountDetailsDto {
    private Long accountId;
    private String accountHolderName;
    private String ifscCode;
    private boolean bankSameAsSender;
}
