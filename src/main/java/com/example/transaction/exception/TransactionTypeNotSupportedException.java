package com.example.transaction.exception;

import com.example.transaction.constants.TransactionStatus;

public class TransactionTypeNotSupportedException extends RuntimeException{

    public TransactionTypeNotSupportedException(String message) {
        super(message);
    }
}
