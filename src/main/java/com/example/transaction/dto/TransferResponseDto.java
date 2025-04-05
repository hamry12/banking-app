package com.example.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDto {
    private String transactionId;
    private String message;
    private LocalDateTime timestamp;
}
