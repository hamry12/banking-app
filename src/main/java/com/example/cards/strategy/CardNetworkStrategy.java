package com.example.cards.strategy;

import com.example.cards.constant.CardNetworkType;

import java.math.BigDecimal;

public interface CardNetworkStrategy {
    public CardNetworkType getCardNetworkType();
    public Double cardIssuanceCharges();
}
