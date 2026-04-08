package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class ProductImage {
    @Id
    private UUID id;

    private String imageId;
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}
