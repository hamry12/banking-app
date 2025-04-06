package com.example.transaction.strategy;

import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class RtgsStrategy implements TransactionStrategy {

    @Override
    public TransactionType getSupportedTransactionType() {
        return TransactionType.RTGS;
    }

    @Override
    public String getMessage() {
        return "Amount Transfer request initiated successfully";
    }

    @Override
    public TransactionStatus getTransactionStatus() {
        return TransactionStatus.PENDING;
    }
}
