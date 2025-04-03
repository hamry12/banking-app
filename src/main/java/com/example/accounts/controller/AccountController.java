package com.example.accounts.controller;

import com.example.accounts.dto.AccountResponseDto;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.AddressRequestDto;
import com.example.accounts.dto.CustomerRegistrationDto;
import com.example.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    /**
     * Endpoint to get account details by account id
     * @param accountId unique identifier of the account
     * @return ResponseEntity containing the account details
     */
    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountDetails(@PathVariable("id") Long accountId) {
        AccountsDto accountDetails= accountService.getAccountDetails(accountId);
        return ResponseEntity.ok(accountDetails);
    }

    /**
     * Endpoint to create a new account for a customer.
     * @param customerRegistrationDto containing the customer details
     * @return ResponseEntity containing the newly created account's ID and creation date
     */
    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
        AccountResponseDto accountDetails= accountService.createAccount(customerRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountDetails);
    }

    @PutMapping("/accounts")
    public ResponseEntity<AccountResponseDto> updateAccountDetails(@RequestParam("accountId") Long accountId,
            @RequestBody CustomerRegistrationDto customerRegistrationDto) {
        AccountResponseDto accountDetails= accountService
                .updateCustomerDetails(accountId, customerRegistrationDto);
        return ResponseEntity.ok(accountDetails);
    }

    @PostMapping("/accounts/{accountId}")
    public ResponseEntity<?> addAddress(@PathVariable("accountId") Long accountId,
                                        @RequestBody AddressRequestDto newAddressRequestDto) {
        AccountResponseDto accountResponseDto=
                accountService.addNewAddress(accountId, newAddressRequestDto);
        return ResponseEntity.ok(accountResponseDto);

    }

}
