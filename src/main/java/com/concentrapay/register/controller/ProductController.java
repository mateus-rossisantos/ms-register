package com.concentrapay.register.controller;

import com.concentrapay.register.model.Product;
import com.concentrapay.register.model.dto.ProductDTO;
import com.concentrapay.register.service.ProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Insere um EC"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Product> postProduct(@RequestBody ProductDTO productDTO) {
        return service.create(productDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupera todos Produtos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{ec}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Product>> getAllProductsOfEc(@PathVariable @CNPJ String ec) {
        //PAGINAR FUTURAMENTE
        return service.getAllProducts(ec);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupera UM ECs"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{ec}/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Product> getEc(@PathVariable @CNPJ String ec, @PathVariable Long id) {
        return service.getProduct(ec, id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Altera um EC"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return service.update(id, productDTO);
    }
}
