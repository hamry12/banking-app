package com.example.transaction.service;

import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import com.example.transaction.dto.*;
import com.example.transaction.entity.ExternalAccounts;
import com.example.transaction.entity.Transactions;
import com.example.transaction.exception.SameAccountException;
import com.example.transaction.repository.ExternalRepository;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.utils.IDGeneratorUtils;
import com.example.transaction.utils.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private IDGeneratorUtils idGeneratorUtils;
    private ExternalRepository externalRepository;

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
        Long fromAccountId=transferRequestDto.getFromAccountId();
        Long toAccountId=transferRequestDto.getReceiverAccountDetails().getAccountId();
        if(Objects.equals(fromAccountId, toAccountId)){
            throw new SameAccountException("Amount can not be transferred to the same account");
        }

        boolean isSameBank=transferRequestDto.getReceiverAccountDetails().isBankSameAsSender();
        TransactionType transferMethod = transferRequestDto.getTransferMethod();
        TransactionStatus transactionStatus;
        String message;
        TransferResponseDto transferResponseDto= new TransferResponseDto();
        String transactionId=idGeneratorUtils.generateId();
        Transactions transactions= transactionMapper.mapToTransactions(transferRequestDto);
        if(transferMethod.equals(TransactionType.IMPS)){
            transactionStatus= TransactionStatus.SUCCESS;
            message="Amount transferred successfully";
        }else{
            transactionStatus=TransactionStatus.PENDING;
            message="Amount transfer requested successfully";
        }
        transactions.setTransactionStatus(transactionStatus);

        transactions.setTransactionId(transactionId);
        transactions.setTotalAmount(
                transactions.getTransactionAmount().add(transactions.getTransactionFee()));
        Transactions savedTransaction = transactionRepository.save(transactions);
        if(!isSameBank){
            ExternalAccounts externalAccounts=
                    transactionMapper.mapToExternalAccounts(transferRequestDto);
            externalAccounts.setTransactions(savedTransaction);
            externalRepository.save(externalAccounts);
        }
        transferResponseDto.setTransactionId(transactionId);
        transferResponseDto.setMessage(message);
        transferResponseDto.setTimestamp(LocalDateTime.now());
        return transferResponseDto;
    }
}
