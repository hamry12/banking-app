package com.example.accounts.exception;

public class PayeeExistException extends RuntimeException{

    public PayeeExistException(String message) {
        super(message);
    }
}
