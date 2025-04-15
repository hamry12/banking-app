package com.example.cards.service;

import com.example.cards.dto.AddCreditCardDto;
import com.example.cards.dto.SuccessResponseDto;

public interface CardsService {
    SuccessResponseDto addNewCreditCard(AddCreditCardDto addCreditCardDto);
}
