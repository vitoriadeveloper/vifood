-- File: src/main/resources/db/migration/V002_070220261947.sql
-- Ajustado para banco com UUID

-- =========================================================
-- 1) Restaurantes: flags, timestamps e colunas do Address
-- =========================================================
ALTER TABLE tb_restaurantes
    ADD COLUMN IF NOT EXISTS ativo BOOLEAN DEFAULT true,
    ADD COLUMN IF NOT EXISTS aberto BOOLEAN DEFAULT false,
    ADD COLUMN IF NOT EXISTS data_cadastro TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    ADD COLUMN IF NOT EXISTS data_atualizacao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    ADD COLUMN IF NOT EXISTS endereco_cep VARCHAR(60),
    ADD COLUMN IF NOT EXISTS endereco_logradouro VARCHAR(255),
    ADD COLUMN IF NOT EXISTS endereco_numero VARCHAR(60),
    ADD COLUMN IF NOT EXISTS endereco_complemento VARCHAR(255),
    ADD COLUMN IF NOT EXISTS endereco_bairro VARCHAR(60),
    ADD COLUMN IF NOT EXISTS endereco_cidade_id UUID;

-- =========================================================
-- FK do endereço para tb_cidades (UUID)
-- =========================================================
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_restaurante_endereco_cidade'
    ) THEN
ALTER TABLE tb_restaurantes
    ADD CONSTRAINT fk_restaurante_endereco_cidade
        FOREIGN KEY (endereco_cidade_id)
            REFERENCES tb_cidades (id);
END IF;
END
$$;

-- =========================================================
-- 2) Produtos: descricao e flag ativo
-- =========================================================
ALTER TABLE tb_produtos
    ADD COLUMN IF NOT EXISTS descricao VARCHAR(255) NOT NULL DEFAULT '',
    ADD COLUMN IF NOT EXISTS ativo BOOLEAN NOT NULL DEFAULT true;

-- =========================================================
-- 3) Usuarios: data de cadastro
-- =========================================================
ALTER TABLE tb_usuarios
    ADD COLUMN IF NOT EXISTS data_cadastro TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now();

-- =========================================================
-- 4) Permissao: campo nome
-- =========================================================
ALTER TABLE tb_permissao
    ADD COLUMN IF NOT EXISTS nome VARCHAR(60);