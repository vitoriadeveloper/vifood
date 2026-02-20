package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "id_cliente", nullable = false)
    private UUID clienteId;

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
