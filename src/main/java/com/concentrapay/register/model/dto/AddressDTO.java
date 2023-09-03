package com.concentrapay.register.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDTO {
    @NotNull
    private String street;
    private int number;
    @NotNull
    private String zipCode;
    @NotNull
    private String city;
    @NotNull
    private String state;
    private String complement;
}
