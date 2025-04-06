package com.example.transaction.exception;

public class SameAccountException extends RuntimeException{
    public SameAccountException(String message) {
        super(message);
    }
}
