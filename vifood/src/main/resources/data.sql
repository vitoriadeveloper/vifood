INSERT INTO tb_cozinhas (nome) VALUES ('Brasileira');
INSERT INTO tb_cozinhas (nome) VALUES ('Italiana');
INSERT INTO tb_cozinhas (nome) VALUES ('Japonesa');
INSERT INTO tb_cozinhas (nome) VALUES ('Mexicana');
INSERT INTO tb_cozinhas (nome) VALUES ('Indiana');
INSERT INTO tb_cozinhas (nome) VALUES ('Chinesa');
INSERT INTO tb_cozinhas (nome) VALUES ('Japonesa');
INSERT INTO tb_cozinhas (nome) VALUES ('Francesa');
INSERT INTO tb_cozinhas (nome) VALUES ('Tailandesa');
INSERT INTO tb_cozinhas (nome) VALUES ('Turca');


-- População da tabela tb_restaurantes
INSERT INTO tb_restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id)
VALUES
('Sabor da Bahia', 5.00, true, true, NOW(), NOW(), 1),  -- Brasileira
('La Trattoria', 10.00, true, true, NOW(), NOW(), 2),   -- Italiana
('Sushi Prime', 7.50, true, false, NOW(), NOW(), 3),    -- Japonesa
('El Sombrero', 6.00, true, true, NOW(), NOW(), 4),     -- Mexicana
('Curry House', 8.00, true, false, NOW(), NOW(), 5),    -- Indiana
('Dragon Wok', 4.50, true, true, NOW(), NOW(), 6),      -- Chinesa
('Tokyo Fusion', 9.00, true, true, NOW(), NOW(), 7),    -- Japonesa (repetida)
('Le Gourmet', 12.00, true, false, NOW(), NOW(), 8),    -- Francesa
('Thai Garden', 5.50, true, true, NOW(), NOW(), 9),     -- Tailandesa
('Istanbul Grill', 6.50, true, true, NOW(), NOW(), 10); -- Turca

-- População da tabela tb_form_pagmto

INSERT INTO tb_form_pagmto (descricao) VALUES ('credito'),('debito'),('pix'), ('dinheiro');