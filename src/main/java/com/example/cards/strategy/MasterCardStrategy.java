package com.example.cards.strategy;

import com.example.cards.constant.CardNetworkType;

public class MasterCardStrategy implements CardNetworkStrategy {
    @Override
    public CardNetworkType getCardNetworkType() {
        return CardNetworkType.MASTERCARD;
    }

    @Override
    public Double cardIssuanceCharges() {
        return 0.15;
    }
}
