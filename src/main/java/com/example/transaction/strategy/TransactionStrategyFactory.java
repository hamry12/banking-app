package com.example.transaction.strategy;

import com.example.transaction.constants.TransactionType;
import com.example.transaction.exception.TransactionTypeNotSupportedException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionStrategyFactory {
    private final Map<TransactionType, TransactionStrategy> transactionStrategyMap = new HashMap<>();

    public TransactionStrategyFactory(List<TransactionStrategy> strategyList){
        strategyList.forEach(strategy ->
                transactionStrategyMap.put(strategy.getSupportedTransactionType(), strategy));
    }

    public TransactionStrategy getStrategy(TransactionType transactionType){
        TransactionStrategy transactionStrategy = transactionStrategyMap.get(transactionType);
        if(transactionStrategy == null){
            throw new TransactionTypeNotSupportedException("Transaction type not supported");
        }
        return transactionStrategy;
    }

}
