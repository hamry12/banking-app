package com.example.cards.utils;

import com.example.cards.dto.AddCreditCardDto;
import com.example.cards.entity.CreditCards;
import org.springframework.stereotype.Component;

@Component
public class CardUtils {
    public CreditCards mapToCreditCards(AddCreditCardDto addCreditCardDto) {
        CreditCards creditCards = new CreditCards();
        creditCards.setCardName(addCreditCardDto.getCardName());
        creditCards.setCardNetworkType(addCreditCardDto.getCardNetworkType());
        creditCards.setJoiningCharges(addCreditCardDto.getJoiningCharges());
        creditCards.setYearlyCharges(addCreditCardDto.getYearlyCharges());
        creditCards.setEffectiveFrom(addCreditCardDto.getEffectiveFrom());
        creditCards.setEffectiveTo(addCreditCardDto.getEffectiveTo());
        return creditCards;
    }
}
