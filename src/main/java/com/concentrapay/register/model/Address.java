package com.concentrapay.register.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
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
