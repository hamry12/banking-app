package com.example.cards.repository;

import com.example.cards.entity.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCards, Long> {
}
