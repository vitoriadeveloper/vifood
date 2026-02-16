-- Add missing restaurante_id column to tb_pedidos table
ALTER TABLE tb_pedidos
    ADD COLUMN IF NOT EXISTS restaurante_id bigint;

-- Add foreign key constraint
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_pedidos_restaurante'
    ) THEN
        ALTER TABLE tb_pedidos
            ADD CONSTRAINT fk_pedidos_restaurante
                FOREIGN KEY (restaurante_id)
                    REFERENCES tb_restaurantes(id);
    END IF;
END
$$;

