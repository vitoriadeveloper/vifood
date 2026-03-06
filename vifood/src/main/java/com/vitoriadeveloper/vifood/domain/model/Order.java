package com.vitoriadeveloper.vifood.domain.model;

import com.vitoriadeveloper.vifood.domain.exceptions.BusinessException;
import com.vitoriadeveloper.vifood.domain.model.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "data_pedido", nullable = false)
    private OffsetDateTime dataPedido = OffsetDateTime.now();

    @PositiveOrZero
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

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

    @Setter
    @Embedded
    private Address enderecoEntrega;


    public void addItem(OrderItem item) {
        item.setPedido(this);
        this.itens.add(item);
    }

    public void removeItem(OrderItem item) {
        item.setPedido(null);
        this.itens.remove(item);
    }

    @PrePersist
    public void prePersistOrderStatus() {
        if (status == null) {
            status = OrderStatus.CRIADO;
        }
    }

    public void confirmOrder() {
        if (status != OrderStatus.CRIADO) {
            throw new BusinessException("Pedido deve estar no status CRIADO para ser confirmado.");
        }
        status = OrderStatus.CONFIRMADO;
    }

    public void cancelOrder() {
        if (status == OrderStatus.ENTREGUE || status == OrderStatus.CANCELADO) {
            throw new BusinessException("Pedido não pode ser cancelado.");
        }
        status = OrderStatus.CANCELADO;
    }

    public void changeStatus(OrderStatus newStatus) {
        if (!canChangeTo(newStatus)) {
            throw new BusinessException(String.format(
                    "Não é possível alterar o status do pedido de %s para %s.",
                    this.status,
                    newStatus
            ));
        }
        this.status = newStatus;
    }

    public boolean canChangeTo(OrderStatus newStatus) {
        return switch (this.status) {
            case CRIADO -> newStatus == OrderStatus.CONFIRMADO;
            case CONFIRMADO -> newStatus == OrderStatus.PREPARANDO || newStatus == OrderStatus.CANCELADO;
            case PRONTO -> newStatus == OrderStatus.SAIU_PARA_ENTREGA;
            case SAIU_PARA_ENTREGA -> newStatus == OrderStatus.ENTREGUE;

            default -> false;
        };
    }


    public void calculateTotalValue() {
        this.valorTotal = itens.stream()
                .map(OrderItem::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = valorTotal.add(restaurante.getTaxaFrete());
    }
}
