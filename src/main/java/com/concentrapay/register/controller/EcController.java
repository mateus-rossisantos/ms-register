package com.concentrapay.register.controller;

import com.concentrapay.register.model.EC;
import com.concentrapay.register.model.dto.EcDTO;
import com.concentrapay.register.service.EcService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecs")
public class EcController {

    private final EcService ecService;

    @Autowired
    public EcController(EcService ecService) {
        this.ecService = ecService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Insere um EC"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EC> postEc(@RequestBody EcDTO ecDTO) {
        return ecService.create(ecDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupera todos ECs"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<EC>> getAllEcs() {
        //PAGINAR FUTURAMENTE
        return ecService.getAllEcs();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupera UM ECs"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<EC> getEc(@PathVariable Long id) {
        return ecService.getEc(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Altera um EC"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EC> update(@PathVariable Long id, @RequestBody EcDTO ecDTO) {
        return ecService.update(id, ecDTO);
    }

}
