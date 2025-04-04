package com.example.accounts.utils;

import com.example.accounts.dto.AddressRequestDto;
import com.example.accounts.dto.CustomerRegistrationDto;
import com.example.accounts.entity.*;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    /**
     * Maps a CustomerRegistrationDto object to a Customers object.
     * @param customerRegistrationDto the source object
     * @param customers the target object
     * @return the updated Customers object
     */
    public Customers mapToCustomer(CustomerRegistrationDto customerRegistrationDto, Customers customers) {
        String email=customerRegistrationDto.getEmail().isBlank()
                ?customers.getEmail()
                :customerRegistrationDto.getEmail();

        String mobile=customerRegistrationDto.getMobile().isBlank()
                ?customers.getMobile()
                :customerRegistrationDto.getMobile();

        String firstName=customerRegistrationDto.getFirstName().isBlank()
                ?customers.getFirstName()
                :customerRegistrationDto.getFirstName();

        String lastName=customerRegistrationDto.getLastName().isBlank()
                ?customers.getLastName()
                :customerRegistrationDto.getLastName();

        customers.setFirstName(firstName);
        customers.setLastName(lastName);
        customers.setEmail(email);
        customers.setMobile(mobile);
        return customers;
    }

    /**
     * Maps a CustomerRegistrationDto object to an Accounts object.
     * @param customerRegistrationDto the source object
     * @param savedCustomer the Customers object that was saved in the database
     * @param accounts the target object
     * @return the updated Accounts object
     */
    public Accounts mapToAccount(CustomerRegistrationDto customerRegistrationDto,
                                 Customers savedCustomer,
                                 Accounts accounts) {
        String branchAddress=customerRegistrationDto.getBranchAddress();
        String ifscCode= customerRegistrationDto.getIfscCode();
        String createdBy=customerRegistrationDto.getCreatedBy();
        AccountType accountType=
                customerRegistrationDto.getAccountType() == 1
                        ?AccountType.SAVINGS
                        :AccountType.CURRENT;
        accounts.setAccountType(accountType);
        accounts.setBranchAddress(branchAddress);
        accounts.setCustomers(savedCustomer);
        accounts.setIfscCode(ifscCode);
        accounts.setCreatedBy(createdBy);
        accounts.setAccountStatus(AccountStatus.PENDING);
        return accounts;
    }

    public Address mapToAddress(AddressRequestDto addressDto, Address address) {
        address.setAddressType(
          addressDto.getAddressType() == 1? AddressType.PERMANENT:AddressType.MAILING
        );
        address.setFirstLine(addressDto.getFirstLine());
        address.setSecondLine(addressDto.getSecondLine());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZip(addressDto.getZip());
        address.setCountry(addressDto.getCountry());
        return address;
    }
}
