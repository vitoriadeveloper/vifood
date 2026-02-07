-- V001__criacao-inicial.sql - PostgreSQL compatible migration

create table tb_cozinhas (
    id bigserial primary key,
    nome varchar(60) not null
);

create table tb_estados (
    id bigserial primary key,
    nome varchar(60) not null,
    sigla varchar(2) not null
);

create table tb_cidades (
    id bigserial primary key,
    nome varchar(60) not null,
    estado_id bigint not null,
    constraint fk_cidade_estado foreign key (estado_id) references tb_estados (id)
);

create table tb_restaurantes (
    id bigserial primary key,
    nome varchar(60) not null,
    taxa_frete numeric(10,2) not null,
    cozinha_id bigint not null,
    constraint fk_restaurante_cozinha foreign key (cozinha_id) references tb_cozinhas (id)
);

create table tb_formas_pagamento (
    id bigserial primary key,
    descricao varchar(60) not null
);

create table tb_restaurante_forma_pagamento (
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    primary key (restaurante_id, forma_pagamento_id),
    constraint fk_rfp_restaurante foreign key (restaurante_id) references tb_restaurantes (id),
    constraint fk_rfp_forma foreign key (forma_pagamento_id) references tb_formas_pagamento (id)
);

create table tb_produtos (
    id bigserial primary key,
    nome varchar(60) not null,
    preco numeric(10,2) not null,
    restaurante_id bigint not null,
    constraint fk_produto_restaurante foreign key (restaurante_id) references tb_restaurantes (id)
);

create table tb_usuarios (
    id bigserial primary key,
    nome varchar(60) not null,
    email varchar(255) not null unique,
    senha varchar(255) not null
);

create table tb_grupos (
    id bigserial primary key,
    nome varchar(60) not null
);

create table tb_usuario_grupo (
    usuario_id bigint not null,
    grupo_id bigint not null,
    primary key (usuario_id, grupo_id),
    constraint fk_ug_usuario foreign key (usuario_id) references tb_usuarios (id),
    constraint fk_ug_grupo foreign key (grupo_id) references tb_grupos (id)
);

create table tb_permissao (
    id bigserial primary key,
    descricao varchar(60) not null
);

create table tb_grupo_permissao (
    grupo_id bigint not null,
    permissao_id bigint not null,
    primary key (grupo_id, permissao_id),
    constraint fk_gp_grupo foreign key (grupo_id) references tb_grupos (id),
    constraint fk_gp_permissao foreign key (permissao_id) references tb_permissao (id)
);
