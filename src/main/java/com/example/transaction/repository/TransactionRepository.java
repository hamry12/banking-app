package com.example.transaction.repository;

import com.example.transaction.entity.Transactions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transactions, String> {
}
