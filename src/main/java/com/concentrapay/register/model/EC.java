package com.concentrapay.register.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String cnpj;
    @NotNull
    private boolean complex;
    @NotNull
    private String pix;
    @NotNull
    private String pixType;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String complexCNPJ;
}
