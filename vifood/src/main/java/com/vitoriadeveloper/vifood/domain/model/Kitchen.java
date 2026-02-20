package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_cozinhas")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kitchen {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    private List<Restaurant> restaurantes = new ArrayList<>();

}
