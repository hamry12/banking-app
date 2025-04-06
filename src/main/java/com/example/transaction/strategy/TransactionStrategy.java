package com.example.transaction.strategy;

import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;

public interface TransactionStrategy {
    /**
     * Retrieves the supported transaction type.
     * @return the transaction type supported by this strategy
     */
    public TransactionType getSupportedTransactionType();

    /**
     * Retrieves the transaction message.
     * @return a message indicating the status or result of the transaction
     */
    public String getMessage();

    /**
     * Retrieves the current transaction status.
     * @return the status of the transaction
     */
    public TransactionStatus getTransactionStatus();
}
