package com.example.cards.strategy;

import com.example.cards.constant.CardNetworkType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
public class RupayCardStrategy implements CardNetworkStrategy {
    @Override
    public CardNetworkType getCardNetworkType() {
        return CardNetworkType.RUPAY;
    }

    @Override
    public Double cardIssuanceCharges() {
        /**
         *  Todo:call third-party api to return the charges
         *  For now we will use mock percentage value 0.15
         */
        return 0.10;
    }
}
