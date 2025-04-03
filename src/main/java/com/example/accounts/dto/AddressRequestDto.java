package com.example.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private String firstLine;
    private String secondLine;
    private String city;
    private String state;
    private String country;
    private String zip;
    private int addressType;
}
