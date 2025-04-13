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
        if (response == null || !response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Account details not found for ID: " + fromAccountId);
        }

        if(Objects.equals(fromAccountId, toAccountId)){
            throw new SameAccountException("Amount can not be transferred to the same account");
        }

        BalanceResponseDto balanceResponseDto=getBalance(fromAccountId);
        BigDecimal amountToBeTransferred=transferRequestDto.getAmountToBeTransferred();
        BigDecimal additionalCharges=transferRequestDto.getAdditionalCharges();
        BigDecimal totalAmount=amountToBeTransferred.add(additionalCharges);
        if(balanceResponseDto.getAvailableBalance()
                .compareTo(totalAmount)<0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        boolean isSameBank=transferRequestDto.getReceiverAccountDetails().isBankSameAsSender();
        TransactionType transferMethod = transferRequestDto.getTransferMethod();
        TransactionStatus transactionStatus;
        String message;
        TransferResponseDto transferResponseDto= new TransferResponseDto();

//        Generate the transaction id
        String transactionId=idGeneratorUtils.generateId();

//        Map the transaction details
        Transactions transactions= transactionMapper.mapToTransactions(transferRequestDto);

//        Fetch transaction status and message
        TransactionStrategy transactionStrategy=
                transactionStrategyFactory.getStrategy(transferMethod);
        transactionStatus=transactionStrategy.getTransactionStatus();
        message=transactionStrategy.getMessage();

//        save transaction information in the database
        transactions.setTransactionStatus(transactionStatus);
        transactions.setTransactionId(transactionId);
        transactions.setTotalAmount(transactions.getTransactionAmount()
                .add(transactions.getTransactionFee()));
        Transactions savedTransaction = transactionRepository.save(transactions);


//        update balance of the sender account
        BigDecimal remainingBalance=balanceResponseDto
                .getAvailableBalance()
                .subtract(totalAmount);
        accountBalanceRepository.updateBalanceByAccountId(fromAccountId,remainingBalance);

//        check if the transaction is between same bank if not save the external account.
        if(!isSameBank){
            ExternalAccounts externalAccounts=
                    transactionMapper.mapToExternalAccounts(transferRequestDto);
            externalAccounts.setTransactions(savedTransaction);
            externalRepository.save(externalAccounts);
        }else{
            BigDecimal receiverBalance=accountBalanceRepository.findBalanceByAccountId(toAccountId);
            receiverBalance=receiverBalance.add(amountToBeTransferred);
            accountBalanceRepository.updateBalanceByAccountId(toAccountId,receiverBalance);
        }

        transferResponseDto.setTransactionId(transactionId);
        transferResponseDto.setMessage(message);
        transferResponseDto.setTimestamp(LocalDateTime.now());
        return transferResponseDto;
    }
}
