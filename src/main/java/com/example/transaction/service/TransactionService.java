package com.example.transaction.service;

import com.example.transaction.dto.*;

public interface TransactionService {
    public BalanceResponseDto getBalance(String accountId);

    TransferResponseDto transferBalance(TransferRequestDto transfferRequestDto);
}
