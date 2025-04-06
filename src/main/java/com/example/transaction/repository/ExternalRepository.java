package com.example.transaction.repository;

import com.example.transaction.entity.ExternalAccounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ExternalRepository extends JpaRepository<ExternalAccounts, Long> {
}
