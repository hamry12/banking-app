package com.example.transaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "external_accounts")
public class ExternalAccounts {

    @Id
    private Long externalAccountId;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id", nullable = false)
    private Transactions transactions;

    private String accountHolderName;
    private String ifscCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
