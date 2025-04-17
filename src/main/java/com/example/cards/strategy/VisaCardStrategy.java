package com.example.cards.strategy;

import com.example.cards.constant.CardNetworkType;

public class VisaCardStrategy implements CardNetworkStrategy {

    @Override
    public CardNetworkType getCardNetworkType() {
        return CardNetworkType.VISA;
    }

    @Override
    public Double cardIssuanceCharges() {
        return 0.20;
    }
}
