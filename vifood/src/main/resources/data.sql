--INSERT INTO tb_cozinhas (nome) VALUES ('Brasileira');
--INSERT INTO tb_cozinhas (nome) VALUES ('Italiana');
--INSERT INTO tb_cozinhas (nome) VALUES ('Japonesa');
--INSERT INTO tb_cozinhas (nome) VALUES ('Mexicana');
--INSERT INTO tb_cozinhas (nome) VALUES ('Indiana');
--INSERT INTO tb_cozinhas (nome) VALUES ('Chinesa');
--INSERT INTO tb_cozinhas (nome) VALUES ('Japonesa');
--INSERT INTO tb_cozinhas (nome) VALUES ('Francesa');
--INSERT INTO tb_cozinhas (nome) VALUES ('Tailandesa');
--INSERT INTO tb_cozinhas (nome) VALUES ('Turca');


-- População da tabela tb_restaurantes
--INSERT INTO tb_restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id)
--VALUES
--('Sabor da Bahia', 5.00, true, true, NOW(), NOW(), 91),  -- Brasileira
--('La Trattoria', 10.00, true, true, NOW(), NOW(), 92),   -- Italiana
--('Sushi Prime', 7.50, true, false, NOW(), NOW(), 93),    -- Japonesa
--('El Sombrero', 6.00, true, true, NOW(), NOW(), 94),     -- Mexicana
--('Curry House', 8.00, true, false, NOW(), NOW(), 95),    -- Indiana
--('Dragon Wok', 4.50, true, true, NOW(), NOW(), 96),      -- Chinesa
--('Tokyo Fusion', 9.00, true, true, NOW(), NOW(), 97),    -- Japonesa (repetida)
--('Le Gourmet', 12.00, true, false, NOW(), NOW(), 98),    -- Francesa
--('Thai Garden', 5.50, true, true, NOW(), NOW(), 99),     -- Tailandesa
--('Istanbul Grill', 6.50, true, true, NOW(), NOW(), 100); -- Turca

-- População da tabela tb_form_pagmto

--INSERT INTO tb_form_pagmto (descricao) VALUES ('credito'),('debito'),('pix'), ('dinheiro');

SELECT 1;

--insert into tb_restaurante_form_pagmto (restaurante_id, forma_pagamento_id) values (103, 1), (103, 2), (103, 3), (108, 3), (108, 2), (108, 3);

--INSERT INTO tb_restaurantes (
--    nome,
--    taxa_frete,
--    ativo,
--    aberto,
--    data_cadastro,
--    data_atualizacao,
--    cozinha_id,
--    endereco_cep,
--    endereco_logradouro,
--    endereco_numero,
--    endereco_complemento,
--    endereco_bairro,
--    endereco_cidade_id
--) VALUES (
--    'Sabor da Bahia',
--    5.00,
--    true,
--    true,
--    NOW(),
--    NOW(),
--    91,
--    '01001-000',
--    'Rua das Laranjeiras',
--    '123',
--    'Apto 45',
--    'Centro',
--    9
--);
