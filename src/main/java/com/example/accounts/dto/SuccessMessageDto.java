package com.example.accounts.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SuccessMessageDto {
    private Long accountId;
    private String message;
    private LocalDateTime createdAt;
}
