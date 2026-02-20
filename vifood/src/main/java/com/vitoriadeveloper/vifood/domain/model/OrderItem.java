package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_itens_pedido")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private Integer quantidade;

    @PositiveOrZero
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @PositiveOrZero
    @Column(name = "preco_total", nullable = false)
    private BigDecimal precoTotal;

    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Order pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Product produto;
}
