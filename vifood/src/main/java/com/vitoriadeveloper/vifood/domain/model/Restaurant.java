package com.vitoriadeveloper.vifood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_restaurantes")
@Getter
@Setter
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    private Boolean ativo;

    private Boolean aberto;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Kitchen cozinha;

    @JsonIgnore
    @Embedded
    private Address endereco;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_restaurante_form_pagmto", joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    private List<PaymentMethod> formasPagamento = new ArrayList<>();
}
