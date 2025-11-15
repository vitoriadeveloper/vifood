package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_cozinhas")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kitchen {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}
