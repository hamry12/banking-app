package com.example.transaction.controller;

import com.example.transaction.dto.*;
import com.example.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * API to get the account balance
     * @param accountId the account identifier
     * @return a response with the balance details
     */
    @GetMapping("/balance/{accountId}")
    public ResponseEntity<BalanceResponseDto> getBalance(@PathVariable("accountId") String accountId) {
        BalanceResponseDto balanceDetails = transactionService.getBalance(accountId);
        return new ResponseEntity<>(balanceDetails, HttpStatus.OK);
    }

    /**
     * API to transfer balance between two accounts
     * @param transferRequestDto the details of the transfer request
     * @return a response with the transaction id and a success message
     */
    @PostMapping("/balance/transfer/")
    public ResponseEntity<?> transferBalance(
            @RequestBody TransferRequestDto transferRequestDto) {
        TransferResponseDto transferResponseDto =
                transactionService.transferBalance(transferRequestDto);
        return ResponseEntity.ok(transferResponseDto);
    }

}
