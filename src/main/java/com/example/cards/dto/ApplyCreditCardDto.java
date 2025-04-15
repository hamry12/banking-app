package com.example.cards.dto;

import com.example.cards.constant.CardNetworkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyCreditCardDto {
    private Long accountId;
    private Long cardId;
}
