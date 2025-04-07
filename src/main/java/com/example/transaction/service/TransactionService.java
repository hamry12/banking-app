package com.example.transaction.service;

import com.example.transaction.dto.*;

public interface TransactionService {
    public BalanceResponseDto getBalance(Long accountId);

    TransferResponseDto transferBalance(TransferRequestDto transfferRequestDto);
}
