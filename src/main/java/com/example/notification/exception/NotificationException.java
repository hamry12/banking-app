package com.example.notification.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotificationException {

    @ExceptionHandler(MessageProcessingException.class)
    public ResponseEntity<ErrorMessageDto> handleMessageProcessingException(MessageProcessingException ex, HttpServletRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(request.getPathInfo(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.internalServerError().body(errorMessageDto);
    }
}
