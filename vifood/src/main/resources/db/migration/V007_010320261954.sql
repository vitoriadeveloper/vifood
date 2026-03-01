create extension if not exists "pgcrypto";

create table tb_restaurante_responsaveis (
                                 restaurante_id uuid not null,
                                 responsavel_id uuid not null,
                                 primary key (restaurante_id, responsavel_id)

);