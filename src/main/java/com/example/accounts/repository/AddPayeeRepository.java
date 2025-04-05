package com.example.accounts.repository;

import com.example.accounts.entity.AddPayee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddPayeeRepository extends JpaRepository<AddPayee, Long> {
    boolean existsByAccounts_accountIdAndReceiverAccountId(Long accountId, Long accountToBeAdded);
}
