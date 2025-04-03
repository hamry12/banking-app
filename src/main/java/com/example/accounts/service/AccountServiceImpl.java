package com.example.accounts.service;

import com.example.accounts.dto.*;
import com.example.accounts.entity.*;
import com.example.accounts.exception.AccountExistException;
import com.example.accounts.exception.AccountNotVerifiedException;
import com.example.accounts.exception.NoSuchAccountExist;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.AddressRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.utils.IdGenerationUtils;
import com.example.accounts.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private MapperUtils mapperUtils;
    private IdGenerationUtils idGenerationUtils;


    /**
     * Retrieves account details for a given account ID.
     *
     * @param accountId the unique identifier of the account
     * @return AccountsDto containing the account details
     */
    @Override
    public AccountsDto getAccountDetails(Long accountId) {
        AccountsDto accountDetails = new AccountsDto();
        Optional<Accounts> account = accountRepository.findById(accountId);
        if(account.isEmpty()) {
            throw new NoSuchAccountExist("Account does not exist with account id: " + accountId);
        }
        String accountStatus = account.get().getAccountStatus().name();
        if(accountStatus.equalsIgnoreCase("PENDING")){
            throw new AccountNotVerifiedException("Account is not verified");
        }
        String customerId = account.get().getCustomerId();
        Optional<Customers> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            accountDetails.setAccountHolderName(
                    customer.get().getFirstName() + " " + customer.get().getLastName()
            );
            accountDetails.setEmail(customer.get().getEmail());
            accountDetails.setMobile(customer.get().getMobile());
        }
        accountDetails.setAccountId(accountId);
        accountDetails.setAccountType(account.get().getAccountType().name());
        accountDetails.setBranchAddress(account.get().getBranchAddress());
        accountDetails.setIfscCode(account.get().getIfscCode());
        return accountDetails;
    }

    /**
     * Creates a new account for a customer.
     *
     * @param customerRegistrationDto containing all the necessary customer details
     * @return AccountCreatedDto containing the newly created account's ID and creation date
     */
    @Override
    public AccountResponseDto createAccount(CustomerRegistrationDto customerRegistrationDto) {
        String email = customerRegistrationDto.getEmail();
        Optional<Customers> customer = customerRepository.findByEmail(email);
        if(customer.isPresent()){
            throw new AccountExistException("Customer already exists with email: " + email);
        }
        String customerId= idGenerationUtils.generateCustomerId(customerRegistrationDto);
        Customers customers= mapperUtils.mapToCustomer(customerRegistrationDto, new Customers());
        customers.setCreatedBy(customerRegistrationDto.getCreatedBy());
        customers.setCustomerId(customerId);

        List<AddressRequestDto> addressList=customerRegistrationDto.getAddresses();
        List<Address> addresses= addressList
                .stream()
                .map(addressDto-> mapperUtils.mapToAddress(addressDto, new Address()))
                .toList();
        addresses.forEach(customers::addAddress);
        Customers savedCustomer=customerRepository.save(customers);

        Long accountId= idGenerationUtils.generateAccountId();
        Accounts accounts=mapperUtils.mapToAccount(customerRegistrationDto, savedCustomer, new Accounts());
        accounts.setAccountId(accountId);
        accountRepository.save(accounts);

        AccountResponseDto accountCreatedDto = new AccountResponseDto();
        accountCreatedDto.setAccountId(accountId);
        accountCreatedDto.setCreatedAt(LocalDateTime.now());
        accountCreatedDto.setMessage("Account created successfully");
        return accountCreatedDto;
    }

    @Override
    public AccountResponseDto updateCustomerDetails(
            Long accountId,
            CustomerRegistrationDto customerRegistrationDto) {

        Optional<Accounts> accountDetails= accountRepository.findById(accountId);
        if(accountDetails.isEmpty()){
            throw new NoSuchAccountExist("Account does not exist with account id: " + accountId);
        }

        Customers customers=customerRepository.findById(accountDetails.get().getCustomerId()).get();
        Customers updatedCustomer= mapperUtils.mapToCustomer(customerRegistrationDto, customers);
        updatedCustomer.setUpdatedBy(customerRegistrationDto.getCreatedBy());
        updatedCustomer.setCustomerId(customers.getCustomerId());
        customerRepository.save(updatedCustomer);

        AccountResponseDto accountCreatedDto = new AccountResponseDto();
        accountCreatedDto.setAccountId(accountId);
        accountCreatedDto.setCreatedAt(LocalDateTime.now());
        accountCreatedDto.setMessage("Customer details updated successfully");
        return accountCreatedDto;
    }

    @Override
    public AccountResponseDto addNewAddress(Long accountId, AddressRequestDto newAddressRequestDto) {
        Optional<Accounts> accounts= accountRepository.findById(accountId);
        if(accounts.isEmpty()){
            throw new NoSuchAccountExist("Account does not exist with account id: " + accountId);
        }
        String customerId= accounts.get().getCustomerId();
        Customers customers= customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException(" Customer Data not found!!"));
        int addressTypeOrdinal= newAddressRequestDto.getAddressType();
        if(addressTypeOrdinal == 1){
            addressRepository.updatePrimaryAddressToMailingAddress(customerId, AddressType.MAILING);
        }
        Address address= mapperUtils.mapToAddress(newAddressRequestDto, new Address());
        address.setCustomers(customers);
        addressRepository.save(address);

        AccountResponseDto accountCreatedDto = new AccountResponseDto();
        accountCreatedDto.setAccountId(accountId);
        accountCreatedDto.setCreatedAt(LocalDateTime.now());
        accountCreatedDto.setMessage("Address added successfully");
        return accountCreatedDto;
    }
}
