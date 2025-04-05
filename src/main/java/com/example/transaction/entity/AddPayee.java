package com.example.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "add_payee")
public class AddPayee {
    @Id
    private Long payeeId;
    private String accountHolderName;
    private Long accountId;
    private String ifscCode;
    private boolean isBankSameAsSender;
}
