-- V001__criacao-inicial.sql - PostgreSQL UUID version

create extension if not exists "pgcrypto";

create table tb_cozinhas (
                             id uuid primary key default gen_random_uuid(),
                             nome varchar(60) not null
);

create table tb_estados (
                            id uuid primary key default gen_random_uuid(),
                            nome varchar(60) not null,
                            sigla varchar(2) not null
);

create table tb_cidades (
                            id uuid primary key default gen_random_uuid(),
                            nome varchar(60) not null,
                            estado_id uuid not null,
                            constraint fk_cidade_estado
                                foreign key (estado_id) references tb_estados (id)
);

create table tb_restaurantes (
                                 id uuid primary key default gen_random_uuid(),
                                 nome varchar(60) not null,
                                 taxa_frete numeric(10,2) not null,
                                 cozinha_id uuid not null,
                                 constraint fk_restaurante_cozinha
                                     foreign key (cozinha_id) references tb_cozinhas (id)
);

create table tb_formas_pagamento (
                                     id uuid primary key default gen_random_uuid(),
                                     descricao varchar(60) not null
);

create table tb_restaurante_forma_pagamento (
                                                restaurante_id uuid not null,
                                                forma_pagamento_id uuid not null,
                                                primary key (restaurante_id, forma_pagamento_id),
                                                constraint fk_rfp_restaurante
                                                    foreign key (restaurante_id) references tb_restaurantes (id),
                                                constraint fk_rfp_forma
                                                    foreign key (forma_pagamento_id) references tb_formas_pagamento (id)
);

create table tb_produtos (
                             id uuid primary key default gen_random_uuid(),
                             nome varchar(60) not null,
                             preco numeric(10,2) not null,
                             restaurante_id uuid not null,
                             constraint fk_produto_restaurante
                                 foreign key (restaurante_id) references tb_restaurantes (id)
);

create table tb_usuarios (
                             id uuid primary key default gen_random_uuid(),
                             nome varchar(60) not null,
                             email varchar(255) not null unique,
                             senha varchar(255) not null
);

create table tb_grupos (
                           id uuid primary key default gen_random_uuid(),
                           nome varchar(60) not null
);

create table tb_usuario_grupo (
                                  usuario_id uuid not null,
                                  grupo_id uuid not null,
                                  primary key (usuario_id, grupo_id),
                                  constraint fk_ug_usuario
                                      foreign key (usuario_id) references tb_usuarios (id),
                                  constraint fk_ug_grupo
                                      foreign key (grupo_id) references tb_grupos (id)
);

create table tb_permissao (
                              id uuid primary key default gen_random_uuid(),
                              descricao varchar(60) not null
);

create table tb_grupo_permissao (
                                    grupo_id uuid not null,
                                    permissao_id uuid not null,
                                    primary key (grupo_id, permissao_id),
                                    constraint fk_gp_grupo
                                        foreign key (grupo_id) references tb_grupos (id),
                                    constraint fk_gp_permissao
                                        foreign key (permissao_id) references tb_permissao (id)
);