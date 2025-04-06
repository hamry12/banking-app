package com.example.transaction.entity;

import com.example.transaction.constants.TransactionDirection;
import com.example.transaction.constants.TransactionStatus;
import com.example.transaction.constants.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;
    private Long fromAccountId;
    private Long toAccountId;
    private TransactionType transactionType;
    private BigDecimal transactionAmount;
    private BigDecimal transactionFee;
    private BigDecimal totalAmount;
    private TransactionStatus transactionStatus;
    private TransactionDirection transactionDirection;
    private boolean isSameBank;
    @CreationTimestamp
    private LocalDateTime transactionDate;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
