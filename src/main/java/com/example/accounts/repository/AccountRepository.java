package com.example.accounts.repository;

import com.example.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Accounts, Long> {


}
