package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @NotNull
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal preco;

    @NotBlank
    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "restaurante_id")
    private Restaurant restaurante;
}
