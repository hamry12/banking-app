package com.example.transaction.exception;

import com.example.transaction.dto.ErrorMessageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method is used to handle the custom exception "NoSuchAccountExist"
     * When this exception is thrown, it means that the account doesn't exist.
     * It returns a ResponseEntity with an ErrorMessageDto object containing the error message and other details.
     * @param ex the exception object
     * @return ResponseEntity of ErrorMessageDto
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleException(
            Exception ex,
            HttpServletRequest request) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle the custom exception "SameAccountException"
     * When this exception is thrown, it means that the transfer request is trying to transfer
     * amount to the same account.
     * It returns a ResponseEntity with an ErrorMessageDto object containing the error message and other details.
     * @param ex the exception object
     * @param request the current HTTP request
     * @return ResponseEntity of ErrorMessageDto
     */
    @ExceptionHandler(SameAccountException.class)
    public ResponseEntity<ErrorMessageDto> handleSameAccountException(
            SameAccountException ex,
            HttpServletRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionTypeNotSupportedException.class)
    public ResponseEntity<ErrorMessageDto> handleTransactionTypeNotSupportedException(
            TransactionTypeNotSupportedException ex,
            HttpServletRequest request) {
            ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                    request.getRequestURL().toString(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorMessageDto> handleInsufficientBalanceException(
            InsufficientBalanceException ex,
            HttpServletRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

}
