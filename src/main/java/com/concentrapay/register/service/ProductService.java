package com.concentrapay.register.service;

import com.concentrapay.register.exception.ECNotFoundException;
import com.concentrapay.register.model.EC;
import com.concentrapay.register.model.Product;
import com.concentrapay.register.model.dto.ProductDTO;
import com.concentrapay.register.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final EcService ecService;
    private final ModelMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, EcService ecService, ModelMapper ecMapper) {
        this.productRepository =  productRepository;
        this.ecService = ecService;
        this.mapper = ecMapper;
    }

    public ResponseEntity<Product> create(ProductDTO productDTO) {

        validateEC(productDTO.getCnpjEc());

        Product productToSave = mapper.map(productDTO, Product.class);

        Product product = productRepository.save(productToSave);
        log.info("Produto "+ product.getName() + " do estabelecimento: " + product.getCnpjEc() + " salvo");

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    private void validateEC(String cnpjEc) {
        Optional<EC> ecOptional = ecService.getEcByCnpj(cnpjEc);

        if (ecOptional.isEmpty()) {
            throw new ECNotFoundException("EC não foi encontrado");
        }
    }


    public ResponseEntity<List<Product>> getAllProducts(String cnpj) {

        List<Product> result = productRepository.findAllByCnpjEc(cnpj);

        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    public ResponseEntity<Product> getProduct(String ec, Long id) {
        Optional<Product> optionalProduct = productRepository.findByIdAndCnpjEc(id, ec);

        if (optionalProduct.isEmpty()) {
            throw new ECNotFoundException("Produto não encontrado");
        }

        return ResponseEntity.ok(optionalProduct.get());
    }

    public ResponseEntity<Product> update(Long id, ProductDTO productDTO) {
        validateEC(productDTO.getCnpjEc());

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ECNotFoundException("Produto não encontrado");
        }

        Product productToUpdate = mapper.map(productDTO, Product.class);

        productToUpdate.setId(optionalProduct.get().getId());

        Product productUpdated = productRepository.save(productToUpdate);

        return ResponseEntity.ok(productUpdated);
    }
}
