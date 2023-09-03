package com.concentrapay.register.repository;

import com.concentrapay.register.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetAndNumberAndCity(String street, int number, String city);
}
