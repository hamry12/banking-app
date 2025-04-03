package com.example.accounts.service;

import com.example.accounts.dto.AccountResponseDto;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.AddressRequestDto;
import com.example.accounts.dto.CustomerRegistrationDto;

public interface AccountService {

    public AccountsDto getAccountDetails(Long accountId);

    AccountResponseDto createAccount(CustomerRegistrationDto customerRegistrationDto);

    AccountResponseDto updateCustomerDetails(Long accountId, CustomerRegistrationDto customerRegistrationDto);

    AccountResponseDto addNewAddress(Long accountId, AddressRequestDto newAddressRequestDto);
}
