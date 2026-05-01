# 🍽️ vifood — API de Delivery de Comida

> Projeto desenvolvido durante a especialização em Spring Boot da **AlgaWorks**.
> API REST inspirada no domínio de delivery de comida, construída com **Spring Boot 3.5.7**, **Java 21** e **Arquitetura Hexagonal (Ports & Adapters)**.

---

## 📑 Sumário

1. [Visão Geral](#-visão-geral)
2. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
3. [Arquitetura](#-arquitetura)
4. [Estrutura de Pastas](#-estrutura-de-pastas)
5. [Modelos de Domínio](#-modelos-de-domínio)
6. [Endpoints da API](#-endpoints-da-api)
7. [Tratamento de Exceções](#-tratamento-de-exceções)
8. [Validações](#-validações)
9. [Migrations (Flyway)](#-migrations-flyway)
10. [Como Executar](#-como-executar)
11. [Testes](#-testes)
12. [Funcionalidades Implementadas](#-funcionalidades-implementadas)

---

## 🌐 Visão Geral

O **vifood** é uma API REST de delivery de comida que permite gerenciar restaurantes, cardápios, pedidos, formas de pagamento, usuários e permissões. O projeto aplica boas práticas de desenvolvimento como Arquitetura Hexagonal, validação com Bean Validation, tratamento global de exceções, paginação, filtros dinâmicos com JPA Specifications e migrations versionadas com Flyway.

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Versão | Propósito |
|------------|--------|-----------|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.5.7 | Framework base |
| Spring Web | — | API REST |
| Spring Data JPA | — | Persistência |
| Spring Validation | — | Bean Validation (Jakarta) |
| Hibernate | — | ORM |
| PostgreSQL | 42.7.7 | Banco de dados |
| Flyway | 10.15.0 | Versionamento de banco |
| Lombok | — | Redução de boilerplate |
| Thymeleaf | — | Template engine |
| Flying Saucer | 9.1.22 | Geração de PDF (HTML → PDF) |
| iText | 2.1.7 | Biblioteca PDF |
| H2 Database | — | Banco em memória (testes) |
| Maven | — | Build/dependências |
| Docker Compose | — | Orquestração local |

**Porta da aplicação:** `8081`

---

## 🏛 Arquitetura

O projeto segue a **Arquitetura Hexagonal (Ports & Adapters)**, com três camadas bem definidas:

```
┌────────────────────────────────────────────────┐
│              INFRA (Adaptadores)               │
│  Controllers REST | JPA Adapters | DTOs        │
│  Mappers | Configurações | Exception Handler   │
└──────────────────┬─────────────────────────────┘
                   │ implementa
                   ▼
┌────────────────────────────────────────────────┐
│          APPLICATION (Casos de Uso)            │
│  Services que implementam as portas de entrada │
└──────────────────┬─────────────────────────────┘
                   │ depende
                   ▼
┌────────────────────────────────────────────────┐
│              DOMAIN (Núcleo)                   │
│  Entidades | Portas (in/out) | Exceções        │
│  Filtros | Sem dependência de Spring           │
└────────────────────────────────────────────────┘
```

### Camadas

- **Domain** — Núcleo do negócio. Entidades, regras, portas (interfaces) e exceções de negócio. **Sem dependência de Spring.**
- **Application** — Implementação dos casos de uso (Services). Orquestra o domínio e usa as portas de saída.
- **Infra** — Adaptadores: REST Controllers, repositórios JPA, DTOs, mappers, configurações, handlers de exceção.

---

## 📁 Estrutura de Pastas

```
src/main/java/com/vitoriadeveloper/vifood/
│
├── domain/
│   ├── model/              # Entidades JPA (Restaurant, Kitchen, Order…)
│   ├── ports/
│   │   ├── in/             # Portas de entrada (casos de uso)
│   │   └── out/            # Portas de saída (repositórios)
│   ├── exceptions/         # Exceções de negócio
│   └── filters/            # Filtros (RestaurantFilter, OrderFilter)
│
├── application/
│   ├── services/           # Implementações dos casos de uso
│   └── config/             # ValidationConfig
│
└── infra/
    ├── adapters/
    │   ├── http/           # REST Controllers
    │   ├── repositories/   # JPA Adapters
    │   └── model/
    │       ├── dto/
    │       │   ├── request/    # DTOs de entrada
    │       │   └── response/   # DTOs de saída
    │       └── mapper/         # Mappers manuais
    ├── repositories/       # Spring Data JPA Repositories
    │   └── spec/           # Specifications (filtros dinâmicos)
    ├── validation/         # Grupos de validação
    ├── config/             # JpaConfig
    ├── exceptions/         # ApiExceptionHandler
    └── utils/              # ErrorResponse
```

---

## 📦 Modelos de Domínio

### Restaurant (`tb_restaurantes`)
Entidade central. Possui nome, taxa de frete, status (ativo/inativo, aberto/fechado), datas de cadastro/atualização, endereço embutido, cozinha, formas de pagamento, produtos e responsáveis.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID | Identificador |
| `nome` | String | Nome do restaurante |
| `taxaFrete` | BigDecimal | Taxa de entrega |
| `ativo` | Boolean | Status ativo |
| `aberto` | Boolean | Status aberto |
| `dataCadastro` | OffsetDateTime | Data de cadastro |
| `dataAtualizacao` | OffsetDateTime | Última atualização |
| `cozinha` | Kitchen (ManyToOne) | Tipo de cozinha |
| `endereco` | Address (Embedded) | Endereço |
| `formasPagamento` | List<PaymentMethod> | Formas aceitas |
| `produtos` | List<Product> | Cardápio |
| `responsaveis` | List<User> | Donos/gestores |

**Métodos:** `open()`, `close()`, `inactive()`, `active()`, `associateRestaurantOwner()`, `disassociateRestaurantOwner()`.

### Kitchen (`tb_cozinhas`)
Tipo/categoria de cozinha (Italiana, Japonesa, etc.). Relacionamento `OneToMany` com Restaurant.

### Product (`tb_produtos`)
Produto do cardápio de um restaurante. Possui nome, descrição, preço, status ativo. Pode ter uma imagem (`OneToOne` com ProductImage).

### Order (`tb_pedidos`)
Pedido feito por um cliente em um restaurante. Possui itens, status (state machine), valor total e endereço de entrega.

**Status do pedido (state machine):**
```
CRIADO → CONFIRMADO → PREPARANDO → PRONTO → SAIU_PARA_ENTREGA → ENTREGUE
              ↓
           CANCELADO  (a partir de CRIADO, CONFIRMADO ou PREPARANDO)
```

**Métodos:** `addItem()`, `removeItem()`, `confirmOrder()`, `cancelOrder()`, `changeStatus()`, `calculateTotalValue()`.

### OrderItem (`tb_itens_pedido`)
Item de um pedido. Quantidade, preço unitário, preço total e observação. Calcula o total via `calculateTotal()`.

### User (`tb_usuarios`)
Usuário do sistema. Possui nome, email único, senha. Está em vários `GroupPermission` (relação `ManyToMany`).

### GroupPermission (`tb_grupos`)
Grupo de permissões. Possui várias `UserPermission`.

### UserPermission (`tb_permissao`)
Permissão atômica do sistema (nome + descrição).

### State (`tb_estados`)
Unidade Federativa: nome + sigla (2 caracteres).

### City (`tb_cidades`)
Cidade vinculada a um State.

### Address (Embeddable)
CEP, logradouro, número, complemento, bairro, cidade. Usado em Restaurant e Order.

### PaymentMethod (`tb_formas_pagamento`)
Forma de pagamento aceita (descrição).

---

## 🔌 Endpoints da API

### 🏛 Estados — `/estados`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/estados` | Lista todos os estados |
| GET | `/estados/{id}` | Busca estado por ID |
| DELETE | `/estados/{id}` | Remove estado |

### 🏙 Cidades — `/cidades`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/cidades` | Cria cidade |
| GET | `/cidades` | Lista cidades |
| GET | `/cidades/{id}` | Busca por ID |
| PUT | `/cidades/{id}` | Atualiza |
| DELETE | `/cidades/{id}` | Remove |

### 🍳 Cozinhas — `/cozinhas`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/cozinhas` | Cria cozinha |
| GET | `/cozinhas?page=0&size=10` | Lista paginada |
| GET | `/cozinhas/{id}` | Busca por ID |
| PUT | `/cozinhas/{id}` | Atualiza |
| DELETE | `/cozinhas/{id}` | Remove |

### 🍴 Restaurantes — `/restaurantes`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/restaurantes` | Cria restaurante |
| GET | `/restaurantes` | Lista (filtros: `nome`, `taxaFreteMin`, `taxaFreteMax`) |
| GET | `/restaurantes/{id}` | Busca por ID |
| PUT | `/restaurantes/{id}` | Atualiza |
| PATCH | `/restaurantes/{id}` | Atualização parcial |
| DELETE | `/restaurantes/{id}` | Remove |
| PUT | `/restaurantes/{id}/ativar` | Ativa restaurante |
| PUT | `/restaurantes/{id}/inativar` | Inativa restaurante |
| POST | `/restaurantes/batch/ativar` | Ativação em lote |
| POST | `/restaurantes/batch/inativar` | Inativação em lote |
| POST | `/restaurantes/{idRestaurante}/formas-pagamento` | Associa forma de pagamento |
| DELETE | `/restaurantes/{idRestaurante}/formas-pagamento/{idFormaPagamento}` | Desassocia forma de pagamento |
| GET | `/restaurantes/{idRestaurante}/responsaveis` | Lista responsáveis |
| POST | `/restaurantes/{idRestaurante}/responsaveis/{idResponsavel}` | Adiciona responsável |
| DELETE | `/restaurantes/{idRestaurante}/responsaveis/{idResponsavel}` | Remove responsável |
| GET | `/restaurantes/{idRestaurante}/produtos` | Lista produtos |
| POST | `/restaurantes/{idRestaurante}/produtos` | Adiciona produto |
| PUT | `/restaurantes/{idRestaurante}/produtos/{idProduto}` | Atualiza produto |
| DELETE | `/restaurantes/{idRestaurante}/produtos/{idProduto}` | Remove produto |

### 💳 Formas de Pagamento — `/formas-pagamento`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/formas-pagamento` | Lista |
| POST | `/formas-pagamento` | Cria |
| GET | `/formas-pagamento/{id}` | Busca |
| DELETE | `/formas-pagamento/{id}` | Remove |

### 👤 Usuários — `/usuarios`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/usuarios` | Lista usuários |
| POST | `/usuarios` | Cria usuário |
| GET | `/usuarios/{id}` | Busca por ID |
| DELETE | `/usuarios/{id}` | Remove |
| PUT | `/usuarios/{usuarioId}/{grupoId}` | Associa usuário a grupo |
| DELETE | `/usuarios/{usuarioId}/{grupoId}` | Desassocia usuário do grupo |

### 🔐 Permissões — `/permissoes`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/permissoes` | Lista |
| POST | `/permissoes` | Cria |
| GET | `/permissoes/{id}` | Busca |
| PUT | `/permissoes/{id}` | Atualiza |
| DELETE | `/permissoes/{id}` | Remove |

### 👥 Grupos — `/grupos`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/grupos` | Lista |
| POST | `/grupos` | Cria |
| GET | `/grupos/{id}` | Busca |
| PUT | `/grupos/{id}` | Atualiza |
| DELETE | `/grupos/{id}` | Remove |
| POST | `/grupos/{grupoId}/permissoes/{permissaoId}` | Adiciona permissão |
| DELETE | `/grupos/{grupoId}/permissoes/{permissaoId}` | Remove permissão |

### 🛒 Pedidos — `/pedidos`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/pedidos` | Cria pedido |
| GET | `/pedidos/{pedidoId}` | Busca por ID |
| GET | `/pedidos/cliente/{clienteId}` | Pedidos por cliente |
| GET | `/pedidos/restaurante/{restauranteId}` | Pedidos por restaurante |
| GET | `/pedidos/filtros` | Filtros (clienteId, restauranteId, status) com paginação |
| PATCH | `/pedidos/{pedidoId}` | Atualização parcial |
| DELETE | `/pedidos/{pedidoId}` | Remove |
| PUT | `/pedidos/{pedidoId}/confirmar` | Confirma pedido (CRIADO → CONFIRMADO) |
| PUT | `/pedidos/{pedidoId}/cancelar` | Cancela pedido |
| PATCH | `/pedidos/{pedidoId}/status?status={status}` | Altera status |

### 📊 Estatísticas — `/estatisticas`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/estatisticas` | Gera estatísticas |
| GET | `/estatisticas` | Consulta estatísticas |

---

## 🚨 Tratamento de Exceções

### Exceções Customizadas (`domain/exceptions/`)
- `BusinessException` — Base para regras de negócio violadas
- `KitchenNotFoundException`, `RestaurantNotFoundException`
- `CityNotFoundException`, `StateNotFoundException`
- `UserNotFoundException`, `OrderNotFoundException`
- `ProductNotFoundException`, `PaymentMethodNotFoundException`
- `GroupPermissionNotFoundException`, `PermissionNotFoundException`
- `InvalidStateReferenceException`

### Handler Global — `ApiExceptionHandler`
Anotado com `@RestControllerAdvice`, estende `ResponseEntityExceptionHandler` e converte exceções em respostas HTTP padronizadas:

| Exceção | Status |
|---------|--------|
| `*NotFoundException` | 404 Not Found |
| `DataIntegrityViolationException` | 409 Conflict |
| `MethodArgumentNotValidException` | 400 Bad Request (com campos) |
| `HttpMessageNotReadableException` | 400 Bad Request |
| `InvalidFormatException` | 400 Bad Request |
| `PropertyBindingException` | 400 Bad Request |
| `MethodArgumentTypeMismatchException` | 400 Bad Request |

### Estrutura de erro (`ErrorResponse`)
```json
{
  "timestamp": "2026-05-01T10:00:00-03:00",
  "status": 400,
  "title": "Dados inválidos",
  "message": "Um ou mais campos estão inválidos",
  "fields": { "nome": "não pode estar em branco" }
}
```

---

## ✅ Validações

- **Bean Validation** (Jakarta): `@NotBlank`, `@NotNull`, `@Valid`, `@PositiveOrZero`
- **Grupos de Validação** (`infra/validation/Groups.java`): `Groups.CozinhaId` para validações condicionais com `@ConvertGroup`
- **Validação em cascata** com `@Valid` em campos aninhados
- **Jackson estrito**: `FAIL_ON_UNKNOWN_PROPERTIES=true` rejeita campos desconhecidos no JSON

---

## 🗄 Migrations (Flyway)

| Versão | Descrição |
|--------|-----------|
| **V001** | Schema inicial: cozinhas, estados, cidades, restaurantes, produtos, formas de pagamento, usuários, grupos, permissões e tabelas de junção |
| **V002** | Flags e timestamps em restaurante (ativo, aberto, dataCadastro, dataAtualizacao), endereço embutido, descrição em produtos |
| **V003** | Tabelas `tb_pedidos` e `tb_itens_pedido` |
| **V004** | FK de produto em itens de pedido + endereço de entrega no pedido |
| **V005** | FK de restaurante em pedidos |
| **V006** | Conversão de timestamps de restaurante para `TIMESTAMP WITH TIME ZONE` |
| **V007** | Tabela `tb_restaurante_responsaveis` (donos de restaurante) |
| **V008** | Tabela de imagens de produtos (`tb_produto_imagens`) |

---

## ▶️ Como Executar

### Pré-requisitos
- Java 21
- Maven 3.9+
- Docker (para PostgreSQL)

### 1. Suba o banco de dados
```bash
docker-compose up -d
```

### 2. Execute a aplicação
```bash
./mvnw spring-boot:run
```
Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

### 3. Acesse
A API estará disponível em `http://localhost:8081`.

### Configuração
- Banco: PostgreSQL em `localhost:5432`, base `vifood`
- Pool HikariCP: max 5, min idle 3
- DDL: `validate` (Hibernate só valida; quem cria é o Flyway)

---

## 🧪 Testes

| Classe | Tipo | O que testa |
|--------|------|-------------|
| `VifoodApplicationTests` | Smoke | Carregamento do contexto Spring |
| `RestaurantControllerTests` | Integração | CRUD, filtros, ativação/inativação |
| `KitchenControllerTests` | Integração | CRUD, paginação |
| `KitchenServiceIT` | Integração | Lógica de serviço, transações |

Plugin **maven-failsafe** (`v3.2.5`) executa testes de integração (sufixo `IT`).

Executar testes:
```bash
./mvnw test
./mvnw verify    # inclui testes de integração
```

---

## ✨ Funcionalidades Implementadas

✅ Cadastro completo de **Restaurantes** com filtros dinâmicos (nome, faixa de taxa de frete)
✅ Operações de **abrir/fechar** e **ativar/inativar** restaurantes (individual e em lote)
✅ Gestão de **Cardápio (Produtos)** por restaurante (com upload de imagem)
✅ Sistema completo de **Pedidos** com state machine de 7 estados
✅ Gerenciamento de **Formas de Pagamento** e associação a restaurantes
✅ Gestão hierárquica de **Localização** (Estados → Cidades)
✅ **Categorização** de restaurantes via Cozinhas
✅ Sistema de **Usuários, Grupos e Permissões**
✅ **Responsáveis (donos)** por restaurante
✅ **Paginação** em listagens de cozinhas e pedidos
✅ **Filtros dinâmicos** via JPA Specifications
✅ **Validações** com Bean Validation + grupos
✅ **Tratamento global de exceções** com mensagens em português
✅ **Migrations versionadas** com Flyway (8 migrations)
✅ **Geração de PDF** (Flying Saucer + Thymeleaf)
✅ **Estatísticas** e relatórios

---

## 👩‍💻 Autora

**Vitória Carolina Dantas**
Projeto desenvolvido como parte da especialização em Spring Boot da [AlgaWorks](https://www.algaworks.com).

📅 Última atualização: Maio/2026
