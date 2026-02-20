create extension if not exists "pgcrypto";

create table tb_pedidos (
                            id uuid primary key default gen_random_uuid(),
                            id_cliente uuid not null,
                            data_pedido timestamp without time zone not null,
                            valor_total decimal(10,2) not null,
                            status varchar(20) not null,
                            constraint fk_pedido_cliente
                                foreign key (id_cliente) references tb_usuarios(id)
);

create table tb_itens_pedido (
                                 id uuid primary key default gen_random_uuid(),
                                 id_pedido uuid not null,
                                 id_produto uuid not null,
                                 quantidade int not null,
                                 observacao varchar(255),
                                 preco_total decimal(10,2) not null,
                                 preco_unitario decimal(10,2) not null,
                                 constraint fk_item_pedido
                                     foreign key (id_pedido) references tb_pedidos(id),
                                 constraint fk_item_produto
                                     foreign key (id_produto) references tb_produtos(id)
);