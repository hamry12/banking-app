package com.example.accounts.repository;

import com.example.accounts.entity.Customers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customers, String> {
    Optional<Customers> findByEmail(String email);

    Optional<Customers> findByMobile(String mobile);
}
