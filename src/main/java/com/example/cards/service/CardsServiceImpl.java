package com.example.cards.service;

import com.example.cards.dto.AddCreditCardDto;
import com.example.cards.dto.SuccessResponseDto;
import com.example.cards.entity.CreditCards;
import com.example.cards.repository.CreditCardRepository;
import com.example.cards.utils.CardUtils;
import com.example.cards.utils.IdGenerationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService{

    private CardUtils cardUtils;
    private IdGenerationUtils idGenerationUtils;
    private CreditCardRepository creditCardRepository;

    @Override
    public SuccessResponseDto addNewCreditCard(AddCreditCardDto addCreditCardDto) {
        SuccessResponseDto successResponseDto= new SuccessResponseDto();
        Long cardId = idGenerationUtils.generateUUID();
        CreditCards creditCards= cardUtils.mapToCreditCards(addCreditCardDto);
        creditCards.setCreditCardId(cardId);
        creditCardRepository.save(creditCards);
        successResponseDto.setMessage("Credit card added successfully");
        successResponseDto.setTimeStamp(LocalDateTime.now());
        return successResponseDto;
    }
}
