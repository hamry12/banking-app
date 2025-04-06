package com.example.transaction.strategy;

import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class ImpsStrategy implements TransactionStrategy {
    @Override
    public TransactionType getSupportedTransactionType() {
        return TransactionType.IMPS;
    }

    @Override
    public String getMessage() {
        return "Amount Transferred Successfully";
    }

    @Override
    public TransactionStatus getTransactionStatus() {
        return TransactionStatus.SUCCESS;
    }
}
