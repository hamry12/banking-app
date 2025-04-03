package com.example.accounts.dto;

import com.example.accounts.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String accountType;
    private String branchAddress;
    private String ifscCode;
    private String createdBy;
    private List<AddressRequestDto> addresses;
}
