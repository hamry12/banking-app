package com.example.notification.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
    private String apiPath;
    private String message;
    private LocalDateTime timestamp;
}
