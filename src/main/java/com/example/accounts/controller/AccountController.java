package com.example.accounts.controller;

import com.example.accounts.dto.*;
import com.example.accounts.service.AccountService;
import jakarta.validation.Valid;
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
        AccountResponseDto accountDetails= accountService.getAccountDetails(accountId);
        return ResponseEntity.ok(accountDetails);
    }

    /**
     * Endpoint to create a new account for a customer.
     * @param customerRegistrationDto containing the customer details
     * @return ResponseEntity containing the newly created account's ID and creation date
     */
    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto) {
        SuccessMessageDto accountDetails= accountService.createAccount(customerRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountDetails);
    }

    /**
     * Endpoint to update customer details for an existing account.
     * @param accountId unique identifier of the account
     * @param customerRegistrationDto containing the updated customer details
     * @return ResponseEntity containing the success message
     */
    @PutMapping("/accounts")
    public ResponseEntity<SuccessMessageDto> updateAccountDetails(@RequestParam("accountId") Long accountId,
                                                                  @RequestBody CustomerRegistrationDto customerRegistrationDto) {
        SuccessMessageDto accountDetails= accountService
                .updateCustomerDetails(accountId, customerRegistrationDto);
        return ResponseEntity.ok(accountDetails);
    }

    /**
     * Endpoint to add a new address to an existing account.
     * @param accountId unique identifier of the account
     * @param newAddressRequestDto containing the new address details
     * @return ResponseEntity containing the success message
     */
    @PostMapping("/address/{accountId}")
    public ResponseEntity<?> addAddress(@PathVariable("accountId") Long accountId,
                                        @RequestBody AddressRequestDto newAddressRequestDto) {
        SuccessMessageDto accountResponseDto=
                accountService.addNewAddress(accountId, newAddressRequestDto);
        return ResponseEntity.ok(accountResponseDto);
    }

    @PostMapping("/accounts/{accountId}/addpayee")
    public ResponseEntity<?> addPayee(
            @PathVariable("accountId") Long accountId,
            @Valid @RequestBody AddPayeeDetailsDto addPayeeDetailsDto) {
        SuccessMessageDto accountResponseDto=
                accountService.addPayee(accountId, addPayeeDetailsDto);
        return ResponseEntity.ok(accountResponseDto);
    }

}
