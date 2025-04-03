package com.example.accounts.repository;

import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.AddressType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Accounts, Long> {


}
