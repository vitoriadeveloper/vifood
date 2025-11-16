package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

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

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Kitchen cozinha;
}
