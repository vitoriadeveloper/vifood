package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

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
