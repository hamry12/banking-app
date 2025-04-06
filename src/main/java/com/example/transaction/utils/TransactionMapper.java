package com.example.transaction.utils;

import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import com.example.transaction.dto.TransferRequestDto;
import com.example.transaction.entity.ExternalAccounts;
import com.example.transaction.entity.Transactions;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {
    public Transactions mapToTransactions(TransferRequestDto transferRequestDto) {
        Transactions transactions = new Transactions();
        transactions.setFromAccountId(transferRequestDto.getFromAccountId());
        transactions.setToAccountId(transferRequestDto.getReceiverAccountDetails().getAccountId());
        transactions.setTransactionAmount(transferRequestDto.getAmountToBeTransferred());
        transactions.setTransactionFee(transferRequestDto.getAdditionalCharges());
        transactions.setTransactionType(transferRequestDto.getTransferMethod());
        transactions.setSameBank(transactions.isSameBank());
        transactions.setTransactionDate(LocalDateTime.now());
        return transactions;
    }

    /**
     * Maps a TransferRequestDto to an ExternalAccounts entity.
     *
     * @param transferRequestDto the transfer request data transfer object
     * @return an ExternalAccounts entity containing the mapped data
     */
    public ExternalAccounts mapToExternalAccounts(TransferRequestDto transferRequestDto) {
        ExternalAccounts externalAccounts = new ExternalAccounts();
        externalAccounts.setAccountHolderName(
                transferRequestDto.getReceiverAccountDetails().getAccountHolderName());
        externalAccounts.setIfscCode(transferRequestDto.getReceiverAccountDetails().getIfscCode());
        externalAccounts.setExternalAccountId(transferRequestDto.getReceiverAccountDetails().getAccountId());
        return externalAccounts;
    }
}
