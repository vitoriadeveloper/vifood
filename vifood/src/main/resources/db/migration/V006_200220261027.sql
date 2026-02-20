ALTER TABLE tb_restaurantes
ALTER COLUMN data_atualizacao TYPE timestamp with time zone
    USING data_atualizacao AT TIME ZONE 'UTC';

ALTER TABLE tb_restaurantes
ALTER COLUMN data_cadastro TYPE timestamp with time zone
    USING data_cadastro AT TIME ZONE 'UTC';