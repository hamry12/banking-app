package com.example.cards.entity;

import com.example.cards.constant.CardNetworkType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardCharges extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "credit_card_id", referencedColumnName = "credit_card_id", nullable = false)
    private CreditCards creditCards;
    private Double cardIssuanceCharges;
}
