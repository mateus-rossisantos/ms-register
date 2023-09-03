package com.concentrapay.register.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    @CNPJ
    private String cnpjEc;
}
