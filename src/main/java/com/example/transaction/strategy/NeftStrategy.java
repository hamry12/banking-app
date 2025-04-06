package com.example.transaction.strategy;

import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class NeftStrategy implements TransactionStrategy {
    @Override
    public TransactionType getSupportedTransactionType() {
        return TransactionType.NEFT;
    }

    @Override
    public String getMessage() {
        return "Amount transfer request initiated successfully";
    }

    @Override
    public TransactionStatus getTransactionStatus() {
        return TransactionStatus.PENDING;
    }
}
