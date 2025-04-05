package com.example.transaction.service;

import com.example.transaction.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private static final String ACCOUNT_BALANCE_URL = "/api/requests/accounts/";

    /**
     * Service to get the account balance.
     *
     * @param accountId the account identifier.
     * @return the balance details.
     */
    @Override
    public BalanceResponseDto getBalance(String accountId) {
        BalanceResponseDto balanceResponseDto= new BalanceResponseDto();
        return balanceResponseDto;
    }

    /**
     * Service to transfer the amount between two accounts.
     *
     * @param transferRequestDto the request details.
     * @return the response details.
     */
    @Override
    public TransferResponseDto transferBalance(TransferRequestDto transferRequestDto) {
        TransferResponseDto transferResponseDto= new TransferResponseDto();
        return transferResponseDto;
    }

    @Override
    public SuccessMessageDto registerPayee(ReceiverAccountDetailsDto receiverAccountDetailsDto) {
        SuccessMessageDto successMessageDto= new SuccessMessageDto();
        return successMessageDto;
    }
}
