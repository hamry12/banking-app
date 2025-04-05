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
}
