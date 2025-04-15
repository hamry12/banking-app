package com.example.cards.controller;

import com.example.cards.dto.AddCreditCardDto;
import com.example.cards.dto.SuccessResponseDto;
import com.example.cards.service.CardsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/requests")
@AllArgsConstructor
public class CardsController {

    private CardsService cardsService;

    @PostMapping("/cards")
    public ResponseEntity<?> addNewCreditCard(@Valid @RequestBody AddCreditCardDto addCreditCardDto) {
        SuccessResponseDto successResponseDto = cardsService.addNewCreditCard(addCreditCardDto);
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }
}
