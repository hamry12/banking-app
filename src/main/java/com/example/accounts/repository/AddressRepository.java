package com.example.accounts.repository;

import com.example.accounts.entity.Address;
import com.example.accounts.entity.AddressType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Query(value = "UPDATE Address ad SET ad.address_type = :addressType WHERE ad.customer_id = :customerId", nativeQuery = true)
    void updatePrimaryAddressToMailingAddress(@Param("customerId") String customerId, @Param("addressType") AddressType addressType);
}
