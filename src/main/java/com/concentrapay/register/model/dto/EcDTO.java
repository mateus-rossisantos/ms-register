package com.concentrapay.register.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class EcDTO {
    @NotNull
    private String name;
    @CNPJ
    private String cnpj;
    @NotNull
    private boolean complex;
    @NotNull
    private String pix;
    @NotNull
    private PixType pixType;
    private String complexCNPJ;
    @NotNull
    private AddressDTO address;
}
