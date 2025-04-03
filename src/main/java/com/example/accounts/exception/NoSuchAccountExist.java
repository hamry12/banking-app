package com.example.accounts.exception;

public class NoSuchAccountExist extends RuntimeException{
    public NoSuchAccountExist(String message) {
        super(message);
    }
}
