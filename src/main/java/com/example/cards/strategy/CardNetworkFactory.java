package com.example.cards.strategy;

import com.example.cards.constant.CardNetworkType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CardNetworkFactory {
    private Map<CardNetworkType, CardNetworkStrategy> map= new HashMap<>();

    public CardNetworkFactory(List<CardNetworkStrategy> cardNetworkStrategyList){
        cardNetworkStrategyList.forEach(cardNetworkStrategy ->
                map.put(cardNetworkStrategy.getCardNetworkType(), cardNetworkStrategy));
    }

    public CardNetworkStrategy getCardNetworkStrategy(CardNetworkType cardNetworkType){
        CardNetworkStrategy strategy = map.get(cardNetworkType);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported card network: " + cardNetworkType);
        }
        return strategy;
    }
}
