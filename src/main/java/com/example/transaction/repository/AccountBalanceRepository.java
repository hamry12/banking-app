package com.example.transaction.repository;

import com.example.transaction.entity.AccountBalance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Transactional
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {

    @Query("select ab.balance from AccountBalance ab where ab.accountId=:accountId")
    BigDecimal findBalanceByAccountId(Long accountId);
}
