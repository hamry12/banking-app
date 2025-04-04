package com.example.accounts.exception;

public class NoPrimaryAddressFound extends RuntimeException{
    public NoPrimaryAddressFound(String message) {
        super(message);
    }
}
