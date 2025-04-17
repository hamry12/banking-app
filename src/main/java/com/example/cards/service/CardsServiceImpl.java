package com.example.cards.service;

import com.example.cards.dto.AddCreditCardDto;
import com.example.cards.dto.SuccessResponseDto;
import com.example.cards.entity.CardCharges;
import com.example.cards.entity.CreditCards;
import com.example.cards.repository.CardChargesRepository;
import com.example.cards.repository.CreditCardRepository;
import com.example.cards.strategy.CardNetworkFactory;
import com.example.cards.strategy.CardNetworkStrategy;
import com.example.cards.utils.CardUtils;
import com.example.cards.utils.IdGenerationUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService{

    private CardUtils cardUtils;
    private IdGenerationUtils idGenerationUtils;
    private CreditCardRepository creditCardRepository;
    private CardNetworkFactory cardNetworkFactory;
    private CardChargesRepository cardChargesRepository;

    @Override
    @Transactional
    public SuccessResponseDto addNewCreditCard(AddCreditCardDto addCreditCardDto) {
        SuccessResponseDto successResponseDto= new SuccessResponseDto();
        Long cardId = idGenerationUtils.generateUUID();
        CreditCards creditCards= cardUtils.mapToCreditCards(addCreditCardDto);
        creditCards.setCreditCardId(cardId);
        CreditCards savedCard = creditCardRepository.save(creditCards);
        CardCharges cardCharges = new CardCharges();

        CardNetworkStrategy cardNetworkStrategy = cardNetworkFactory.getCardNetworkStrategy(addCreditCardDto.getCardNetworkType());
        // save card charges details
        cardCharges.setCreditCards(savedCard);
        cardCharges.setCardIssuanceCharges(cardNetworkStrategy.cardIssuanceCharges());
        cardChargesRepository.save(cardCharges);
        successResponseDto.setMessage("Credit card added successfully");
        successResponseDto.setTimeStamp(LocalDateTime.now());
        return successResponseDto;
    }
}
