package com.example.transaction.service;

import com.example.transaction.client.AccountFeignClients;
import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import com.example.transaction.dto.*;
import com.example.transaction.entity.ExternalAccounts;
import com.example.transaction.entity.Transactions;
import com.example.transaction.exception.InsufficientBalanceException;
import com.example.transaction.exception.SameAccountException;
import com.example.transaction.repository.AccountBalanceRepository;
import com.example.transaction.repository.ExternalRepository;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.strategy.TransactionStrategy;
import com.example.transaction.strategy.TransactionStrategyFactory;
import com.example.transaction.utils.IDGeneratorUtils;
import com.example.transaction.utils.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private IDGeneratorUtils idGeneratorUtils;
    private ExternalRepository externalRepository;
    private TransactionStrategyFactory transactionStrategyFactory;
    private AccountBalanceRepository accountBalanceRepository;
    private AccountFeignClients accountFeignClients;

    /**
     * Service to get the account balance.
     *
     * @param accountId the account identifier.
     * @return the balance details.
     */
    @Override
    public BalanceResponseDto getBalance(Long accountId) {
        BalanceResponseDto balanceResponseDto= new BalanceResponseDto();
        BigDecimal balanceByAccountId = accountBalanceRepository.findBalanceByAccountId(accountId);
        if(balanceByAccountId==null){
            balanceByAccountId=new BigDecimal(0);
        }
        balanceResponseDto.setAvailableBalance(balanceByAccountId);
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

        ResponseEntity<AccountResponseDto> response = accountFeignClients.getAccountDetails(fromAccountId);
        if (response == null || response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Account details not found for ID: " + fromAccountId);
        }


        if(Objects.equals(fromAccountId, toAccountId)){
            throw new SameAccountException("Amount can not be transferred to the same account");
        }

        /**
         * Todo: Add the logic to check the balance before transfer
         */
        BalanceResponseDto balanceResponseDto=getBalance(fromAccountId);
        if(balanceResponseDto.getAvailableBalance()
                .compareTo(transferRequestDto.getAmountToBeTransferred())<0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        boolean isSameBank=transferRequestDto.getReceiverAccountDetails().isBankSameAsSender();
        TransactionType transferMethod = transferRequestDto.getTransferMethod();
        TransactionStatus transactionStatus;
        String message;
        TransferResponseDto transferResponseDto= new TransferResponseDto();
        String transactionId=idGeneratorUtils.generateId();
        Transactions transactions= transactionMapper.mapToTransactions(transferRequestDto);

        /**
         * Todo: Add the design pattern to handle the different transfer methods and update the transaction status
         * uncomment this section only if you do not want design pattern based implementation
         * if(transferMethod.equals(TransactionType.IMPS)){
         *             transactionStatus= TransactionStatus.SUCCESS;
         *             message="Amount transferred successfully";
         *         }else{
         *             transactionStatus=TransactionStatus.PENDING;
         *             message="Amount transfer requested successfully";
         *         }
         */
        TransactionStrategy transactionStrategy=
                transactionStrategyFactory.getStrategy(transferMethod);
        transactionStatus=transactionStrategy.getTransactionStatus();
        message=transactionStrategy.getMessage();

        transactions.setTransactionStatus(transactionStatus);
        transactions.setTransactionId(transactionId);
        transactions.setTotalAmount(transactions.getTransactionAmount()
                .add(transactions.getTransactionFee()));
        Transactions savedTransaction = transactionRepository.save(transactions);
        if(!isSameBank){
            ExternalAccounts externalAccounts=
                    transactionMapper.mapToExternalAccounts(transferRequestDto);
            externalAccounts.setTransactions(savedTransaction);
            externalRepository.save(externalAccounts);
        }

        /**
         * Todo: Add the logic to update the balance
         */
        transferResponseDto.setTransactionId(transactionId);
        transferResponseDto.setMessage(message);
        transferResponseDto.setTimestamp(LocalDateTime.now());
        return transferResponseDto;
    }
}
