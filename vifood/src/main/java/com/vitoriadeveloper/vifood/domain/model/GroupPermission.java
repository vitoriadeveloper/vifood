package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_grupos")
@Getter
@Setter
public class GroupPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String nome;


    @ManyToMany
    @JoinTable(name = "tb_grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<UserPermission> permissoes = new ArrayList<>();

    public void addPermission(UserPermission permission) {
        if (!this.permissoes.contains(permission)) {
            this.permissoes.add(permission);
        }
    }

    public void removePermission(UserPermission permission) {
        this.permissoes.remove(permission);
    }
}
