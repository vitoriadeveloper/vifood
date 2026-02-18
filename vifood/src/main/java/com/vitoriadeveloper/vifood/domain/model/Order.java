package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_pedidos")
@Getter
@Setter
public class Order {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private Integer clienteId;

    @Column(name = "data_pedido", nullable = false)
    private Date dataPedido;

    @PositiveOrZero
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> itens = new ArrayList<>();

    @ManyToOne
    private Restaurant restaurante;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private User cliente;

    @Embedded
    private Address enderecoEntrega;
}
