package com.vitoriadeveloper.vifood.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    private String senha;

    @CreationTimestamp
    @Column(name = "data_cadastro")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "tb_usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<GroupPermission> grupos = new ArrayList<>();


    public void addUserToGroupPermission(GroupPermission groupPermission) {
        if(!this.grupos.contains(groupPermission)) {
            this.grupos.add(groupPermission);
        }
    }

    public void removeUserToGroupPermission(GroupPermission groupPermission) {
        this.grupos.remove(groupPermission);
    }
}
