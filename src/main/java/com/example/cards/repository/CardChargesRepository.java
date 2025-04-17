package com.example.cards.repository;

import com.example.cards.entity.CardCharges;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardChargesRepository extends JpaRepository<CardCharges, Long> {
}
