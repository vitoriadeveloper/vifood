create table tb_pedidos (
                            id bigint primary key,
                            id_cliente int not null,
                            data_pedido timestamp not null,
                            valor_total decimal(10,2) not null,
                            status varchar(20) not null
);

create table tb_itens_pedido (
                                 id bigint primary key,
                                 id_pedido bigint  not null,
                                 id_produto int not null,
                                 quantidade int not null,
                                 observacao varchar(255),
                                 preco_total decimal(10,2) not null,
                                 preco_unitario decimal(10,2) not null,
                                 foreign key (id_pedido) references tb_pedidos(id),
                                 foreign key (id_produto) references tb_produtos(id)
);