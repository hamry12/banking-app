package com.example.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customers extends BaseEntity{

    @Id
    @Column(name = "customer_id")
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    /**
     * Add an address to the customer.
     *
     * @param address the address to be added
     */
    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomers(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setCustomers(null);
    }
}
