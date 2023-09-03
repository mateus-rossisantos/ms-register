package com.concentrapay.register.repository;

import com.concentrapay.register.model.EC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ECRepository extends JpaRepository<EC, Long> {

    Optional<EC> findByCnpj(String cnpj);
}
