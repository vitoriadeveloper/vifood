INSERT INTO tb_cozinhas (id, nome) VALUES
                                       (1, 'Brasileira'),
                                       (2, 'Italiana'),
                                       (3, 'Japonesa');
INSERT INTO tb_estados (id, nome, sigla) VALUES
                                             (1, 'São Paulo', 'SP'),
                                             (2, 'Bahia', 'BA');
INSERT INTO tb_cidades (id, nome, estado_id) VALUES
                                                 (1, 'São Paulo', 1),
                                                 (2, 'Campinas', 1),
                                                 (3, 'Salvador', 2);
INSERT INTO tb_restaurantes (
    id, nome, taxa_frete, cozinha_id,
    ativo, aberto, data_cadastro, data_atualizacao,
    endereco_cep, endereco_logradouro, endereco_numero,
    endereco_complemento, endereco_bairro, endereco_cidade_id
) VALUES
      (
          1, 'Restaurante Sabor Brasil', 10.50, 1,
          true, true, now(), now(),
          '01001-000', 'Rua das Flores', '100',
          'Sala 10', 'Centro', 1
      ),
      (
          2, 'Cantina Italiana', 15.00, 2,
          true, false, now(), now(),
          '13010-000', 'Av. Itália', '200',
          NULL, 'Jardins', 2
      ),
      (
          3, 'Sushi House', 20.00, 3,
          true, true, now(), now(),
          '40010-000', 'Rua Japão', '300',
          'Loja B', 'Comércio', 3
      );
INSERT INTO tb_formas_pagamento (id, descricao) VALUES
                                                    (1, 'Cartão de Crédito'),
                                                    (2, 'Cartão de Débito'),
                                                    (3, 'Dinheiro'),
                                                    (4, 'PIX');
INSERT INTO tb_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES
                                                                                    (1, 1),
                                                                                    (1, 4),
                                                                                    (2, 1),
                                                                                    (2, 2),
                                                                                    (3, 1),
                                                                                    (3, 3),
                                                                                    (3, 4);
INSERT INTO tb_produtos (
    id, nome, descricao, preco, ativo, restaurante_id
) VALUES
      (1, 'Feijoada', 'Feijoada completa com acompanhamentos', 45.00, true, 1),
      (2, 'Lasagna', 'Lasagna à bolonhesa', 38.00, true, 2),
      (3, 'Sushi Combo', 'Combo com 20 peças variadas', 60.00, true, 3);
INSERT INTO tb_usuarios (
    id, nome, email, senha, data_cadastro
) VALUES
      (1, 'Administrador', 'admin@vifood.com', '123456', now()),
      (2, 'Cliente Teste', 'cliente@vifood.com', '123456', now());
INSERT INTO tb_grupos (id, nome) VALUES
                                     (1, 'ADMIN'),
                                     (2, 'CLIENTE');
INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES
                                                        (1, 1),
                                                        (2, 2);
INSERT INTO tb_permissao (id, nome, descricao) VALUES
                                                   (1, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes'),
                                                   (2, 'EDITAR_RESTAURANTES', 'Permite editar restaurantes'),
                                                   (3, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES
                                                            (1, 1),
                                                            (1, 2),
                                                            (1, 3),
                                                            (2, 1);
