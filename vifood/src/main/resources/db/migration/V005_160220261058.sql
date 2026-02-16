-- Convert existing id_produto column from int to bigint
ALTER TABLE tb_itens_pedido
ALTER COLUMN id_produto TYPE bigint;

-- Add fk_itens_pedido_produto constraint if not exists
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_itens_pedido_produto'
    ) THEN
ALTER TABLE tb_itens_pedido
    ADD CONSTRAINT fk_itens_pedido_produto
        FOREIGN KEY (id_produto)
            REFERENCES tb_produtos(id);
END IF;
END
$$;

-- Convert existing id_cliente column from int to bigint to match User.id type
ALTER TABLE tb_pedidos
ALTER COLUMN id_cliente TYPE bigint;

ALTER TABLE tb_pedidos
    ADD COLUMN IF NOT EXISTS endereco_cep varchar(20),
    ADD COLUMN IF NOT EXISTS endereco_logradouro varchar(255),
    ADD COLUMN IF NOT EXISTS endereco_numero varchar(20),
    ADD COLUMN IF NOT EXISTS endereco_complemento varchar(255),
    ADD COLUMN IF NOT EXISTS endereco_bairro varchar(100),
    ADD COLUMN IF NOT EXISTS endereco_cidade_id bigint;

-- Add fk_pedido_endereco_cidade constraint if not exists
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_pedido_endereco_cidade'
    ) THEN
ALTER TABLE tb_pedidos
    ADD CONSTRAINT fk_pedido_endereco_cidade
        FOREIGN KEY (endereco_cidade_id)
            REFERENCES tb_cidades(id);
END IF;
END
$$;
