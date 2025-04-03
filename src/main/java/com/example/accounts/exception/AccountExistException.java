package com.example.accounts.exception;

public class AccountExistException extends RuntimeException{
    public AccountExistException(String message) {
        super(message);
    }
}
