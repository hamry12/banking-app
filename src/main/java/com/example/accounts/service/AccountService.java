package com.example.accounts.service;

import com.example.accounts.dto.*;

public interface AccountService {

    public AccountResponseDto getAccountDetails(Long accountId);

    SuccessMessageDto createAccount(CustomerRegistrationDto customerRegistrationDto);

    SuccessMessageDto updateCustomerDetails(Long accountId, CustomerRegistrationDto customerRegistrationDto);

    SuccessMessageDto addNewAddress(Long accountId, AddressRequestDto newAddressRequestDto);
}
