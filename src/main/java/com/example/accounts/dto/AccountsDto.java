package com.example.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    private Long accountId;
    private String accountType;
    private String branchAddress;
    private String accountHolderName;
    private String email;
    private String mobile;
    private String ifscCode;
}
