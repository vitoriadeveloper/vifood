CREATE TABLE tb_produto_imagens (
                               product_id UUID NOT NULL,
                               image_id VARCHAR(255),
                               description VARCHAR(255),

                               CONSTRAINT pk_produto_imagens PRIMARY KEY (product_id),
                               CONSTRAINT fk_produto_imagens_product
                                   FOREIGN KEY (product_id) REFERENCES tb_produtos(id)
);