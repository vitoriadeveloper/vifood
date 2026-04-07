package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
    private Product product;
}
