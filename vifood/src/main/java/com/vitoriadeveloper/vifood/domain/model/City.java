package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_cidades")
@Getter
@Setter
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private State estado;
}
