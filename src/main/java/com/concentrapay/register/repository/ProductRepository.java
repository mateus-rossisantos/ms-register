package com.concentrapay.register.repository;

import com.concentrapay.register.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCnpjEc(String cnpj);

    Optional<Product> findByIdAndCnpjEc(Long id, String ec);
}
