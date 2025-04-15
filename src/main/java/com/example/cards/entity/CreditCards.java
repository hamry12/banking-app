package com.example.cards.entity;

import com.example.cards.constant.CardNetworkType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCards extends BaseEntity{

    @Id
    private Long creditCardId;
    private String cardName;
    private CardNetworkType cardNetworkType;
    private BigDecimal joiningCharges;
    private BigDecimal yearlyCharges;
    private LocalDateTime effectiveFrom;
    private LocalDateTime effectiveTo;
}
