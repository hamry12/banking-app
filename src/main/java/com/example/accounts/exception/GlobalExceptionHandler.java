package com.example.accounts.exception;

import com.example.accounts.dto.ErrorMessageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handles AccountExistException and returns a bad request response.
     *
     * @param ex the AccountExistException instance
     * @param request the HttpServletRequest object
     * @return ResponseEntity containing an ErrorMessageDto with error details
     */
    @ExceptionHandler(AccountExistException.class)
    public ResponseEntity<ErrorMessageDto> handleAccountExistsException(
            AccountExistException ex,
            HttpServletRequest request) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(errorMessageDto);
    }

    /**
     * Handles NoSuchAccountExist exceptions and returns a bad request response.
     *
     * @param ex the NoSuchAccountExist instance
     * @param request the HttpServletRequest object
     * @return ResponseEntity containing an ErrorMessageDto with error details
     */
    @ExceptionHandler(NoSuchAccountExist.class)
    public ResponseEntity<ErrorMessageDto> handleNoSuchAccountExistException(
            NoSuchAccountExist ex,
            HttpServletRequest request) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles AccountNotVerifiedException and returns a bad request response.
     *
     * @param ex the AccountNotVerifiedException instance
     * @param request the HttpServletRequest object
     * @return ResponseEntity containing an ErrorMessageDto with error details
     */
    @ExceptionHandler(AccountNotVerifiedException.class)
    public ResponseEntity<ErrorMessageDto> handleAccountNotVerifiedException(
            AccountNotVerifiedException ex,
            HttpServletRequest request) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any other Exception and returns an internal server error response.
     *
     * @param ex the Exception instance
     * @param request the HttpServletRequest object
     * @return ResponseEntity containing an ErrorMessageDto with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleException(Exception ex, HttpServletRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles NoPrimaryAddressFound exceptions and returns a bad request response.
     *
     * @param ex the NoPrimaryAddressFound instance
     * @param request the HttpServletRequest object
     * @return ResponseEntity containing an ErrorMessageDto with error details
     */
    @ExceptionHandler(NoPrimaryAddressFound.class)
    public ResponseEntity<ErrorMessageDto> handlePrimaryAddressNotFound(
            NoPrimaryAddressFound ex,
            HttpServletRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles PayeeExistException and returns a bad request response.
     *
     * @param ex      the PayeeExistException instance
     * @param request the HttpServletRequest object
     * @return ResponseEntity containing an ErrorMessageDto with error details
     */
    @ExceptionHandler(PayeeExistException.class)
    public ResponseEntity<ErrorMessageDto> handlePayeeExistException(
            PayeeExistException ex,
            HttpServletRequest request) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                request.getRequestURL().toString(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a bad request response.
     * Extracts the error messages from the binding result and returns them in a map.
     *
     * @param ex the MethodArgumentNotValidException instance
     * @return ResponseEntity containing a map of error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
