# 🍽️ vifood — Food Delivery API

> Project developed during the **AlgaWorks** Spring Boot specialization course.
> REST API inspired by the food delivery domain, built with **Spring Boot 3.5.7**, **Java 21**, and **Hexagonal Architecture (Ports & Adapters)**.

---

## 📑 Table of Contents

1. [Overview](#-overview)
2. [Tech Stack](#-tech-stack)
3. [Architecture](#-architecture)
4. [Project Structure](#-project-structure)
5. [Domain Models](#-domain-models)
6. [API Endpoints](#-api-endpoints)
7. [Exception Handling](#-exception-handling)
8. [Validation](#-validation)
9. [Database Migrations (Flyway)](#-database-migrations-flyway)
10. [How to Run](#-how-to-run)
11. [Tests](#-tests)
12. [Implemented Features](#-implemented-features)

---

## 🌐 Overview

**vifood** is a food delivery REST API for managing restaurants, menus, orders, payment methods, users, and permissions. The project applies modern best practices such as Hexagonal Architecture, Bean Validation, global exception handling, pagination, dynamic filters via JPA Specifications, and versioned database migrations with Flyway.

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Main language |
| Spring Boot | 3.5.7 | Base framework |
| Spring Web | — | REST API |
| Spring Data JPA | — | Persistence |
| Spring Validation | — | Bean Validation (Jakarta) |
| Hibernate | — | ORM |
| PostgreSQL | 42.7.7 | Database |
| Flyway | 10.15.0 | Schema versioning |
| Lombok | — | Boilerplate reduction |
| Thymeleaf | — | Template engine |
| Flying Saucer | 9.1.22 | PDF generation (HTML → PDF) |
| iText | 2.1.7 | PDF library |
| H2 Database | — | In-memory DB (testing) |
| Maven | — | Build tool |
| Docker Compose | — | Local orchestration |

**Application port:** `8081`

---

## 🏛 Architecture

The project follows **Hexagonal Architecture (Ports & Adapters)** with three well-defined layers:

```
┌────────────────────────────────────────────────┐
│             INFRA (Adapters)                   │
│  REST Controllers | JPA Adapters | DTOs        │
│  Mappers | Configurations | Exception Handler  │
└──────────────────┬─────────────────────────────┘
                   │ implements
                   ▼
┌────────────────────────────────────────────────┐
│           APPLICATION (Use Cases)              │
│  Services that implement the inbound ports     │
└──────────────────┬─────────────────────────────┘
                   │ depends on
                   ▼
┌────────────────────────────────────────────────┐
│              DOMAIN (Core)                     │
│  Entities | Ports (in/out) | Exceptions        │
│  Filters | No Spring dependencies              │
└────────────────────────────────────────────────┘
```

### Layers

- **Domain** — Business core. Entities, business rules, ports (interfaces), and business exceptions. **No Spring dependencies.**
- **Application** — Use case implementations (Services). Orchestrates domain logic and uses outbound ports.
- **Infra** — Adapters: REST Controllers, JPA repositories, DTOs, mappers, configurations, exception handlers.

---

## 📁 Project Structure

```
src/main/java/com/vitoriadeveloper/vifood/
│
├── domain/
│   ├── model/              # JPA entities (Restaurant, Kitchen, Order…)
│   ├── ports/
│   │   ├── in/             # Inbound ports (use cases)
│   │   └── out/            # Outbound ports (repositories)
│   ├── exceptions/         # Business exceptions
│   └── filters/            # Filters (RestaurantFilter, OrderFilter)
│
├── application/
│   ├── services/           # Use case implementations
│   └── config/             # ValidationConfig
│
└── infra/
    ├── adapters/
    │   ├── http/           # REST Controllers
    │   ├── repositories/   # JPA Adapters
    │   └── model/
    │       ├── dto/
    │       │   ├── request/    # Input DTOs
    │       │   └── response/   # Output DTOs
    │       └── mapper/         # Manual mappers
    ├── repositories/       # Spring Data JPA Repositories
    │   └── spec/           # Specifications (dynamic filters)
    ├── validation/         # Validation groups
    ├── config/             # JpaConfig
    ├── exceptions/         # ApiExceptionHandler
    └── utils/              # ErrorResponse
```

---

## 📦 Domain Models

### Restaurant (`tb_restaurantes`)
The core entity. Has name, delivery fee, status (active/inactive, open/closed), creation/update timestamps, embedded address, kitchen, payment methods, products, and owners.

| Field | Type | Description |
|-------|------|-------------|
| `id` | UUID | Identifier |
| `nome` | String | Restaurant name |
| `taxaFrete` | BigDecimal | Delivery fee |
| `ativo` | Boolean | Active flag |
| `aberto` | Boolean | Open flag |
| `dataCadastro` | OffsetDateTime | Creation timestamp |
| `dataAtualizacao` | OffsetDateTime | Last update timestamp |
| `cozinha` | Kitchen (ManyToOne) | Kitchen type |
| `endereco` | Address (Embedded) | Address |
| `formasPagamento` | List<PaymentMethod> | Accepted payments |
| `produtos` | List<Product> | Menu |
| `responsaveis` | List<User> | Owners/managers |

**Methods:** `open()`, `close()`, `inactive()`, `active()`, `associateRestaurantOwner()`, `disassociateRestaurantOwner()`.

### Kitchen (`tb_cozinhas`)
Kitchen type/category (Italian, Japanese, etc.). `OneToMany` relationship with Restaurant.

### Product (`tb_produtos`)
Menu item belonging to a restaurant. Has name, description, price, active flag. Can have an image (`OneToOne` with ProductImage).

### Order (`tb_pedidos`)
Order placed by a client at a restaurant. Has items, status (state machine), total value, and delivery address.

**Order status (state machine):**
```
CRIADO → CONFIRMADO → PREPARANDO → PRONTO → SAIU_PARA_ENTREGA → ENTREGUE
   (created) (confirmed)  (preparing)  (ready)  (out for delivery)  (delivered)
              ↓
           CANCELADO  (from CRIADO, CONFIRMADO, or PREPARANDO)
            (cancelled)
```

**Methods:** `addItem()`, `removeItem()`, `confirmOrder()`, `cancelOrder()`, `changeStatus()`, `calculateTotalValue()`.

### OrderItem (`tb_itens_pedido`)
Line item in an order. Quantity, unit price, total price, and notes. Computes total via `calculateTotal()`.

### User (`tb_usuarios`)
System user. Has name, unique email, password. Belongs to multiple `GroupPermission` entries (`ManyToMany`).

### GroupPermission (`tb_grupos`)
Permission group. Holds multiple `UserPermission` entries.

### UserPermission (`tb_permissao`)
Atomic system permission (name + description).

### State (`tb_estados`)
Federative unit: name + 2-letter code.

### City (`tb_cidades`)
City linked to a State.

### Address (Embeddable)
ZIP code, street, number, complement, neighborhood, city. Used in Restaurant and Order.

### PaymentMethod (`tb_formas_pagamento`)
Accepted payment method (description).

---

## 🔌 API Endpoints

### 🏛 States — `/estados`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/estados` | List all states |
| GET | `/estados/{id}` | Get state by ID |
| DELETE | `/estados/{id}` | Delete state |

### 🏙 Cities — `/cidades`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/cidades` | Create city |
| GET | `/cidades` | List cities |
| GET | `/cidades/{id}` | Get by ID |
| PUT | `/cidades/{id}` | Update |
| DELETE | `/cidades/{id}` | Delete |

### 🍳 Kitchens — `/cozinhas`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/cozinhas` | Create kitchen |
| GET | `/cozinhas?page=0&size=10` | List (paginated) |
| GET | `/cozinhas/{id}` | Get by ID |
| PUT | `/cozinhas/{id}` | Update |
| DELETE | `/cozinhas/{id}` | Delete |

### 🍴 Restaurants — `/restaurantes`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/restaurantes` | Create restaurant |
| GET | `/restaurantes` | List (filters: `nome`, `taxaFreteMin`, `taxaFreteMax`) |
| GET | `/restaurantes/{id}` | Get by ID |
| PUT | `/restaurantes/{id}` | Update |
| PATCH | `/restaurantes/{id}` | Partial update |
| DELETE | `/restaurantes/{id}` | Delete |
| PUT | `/restaurantes/{id}/ativar` | Activate |
| PUT | `/restaurantes/{id}/inativar` | Deactivate |
| POST | `/restaurantes/batch/ativar` | Batch activate |
| POST | `/restaurantes/batch/inativar` | Batch deactivate |
| POST | `/restaurantes/{idRestaurante}/formas-pagamento` | Associate payment method |
| DELETE | `/restaurantes/{idRestaurante}/formas-pagamento/{idFormaPagamento}` | Disassociate payment method |
| GET | `/restaurantes/{idRestaurante}/responsaveis` | List owners |
| POST | `/restaurantes/{idRestaurante}/responsaveis/{idResponsavel}` | Add owner |
| DELETE | `/restaurantes/{idRestaurante}/responsaveis/{idResponsavel}` | Remove owner |
| GET | `/restaurantes/{idRestaurante}/produtos` | List products |
| POST | `/restaurantes/{idRestaurante}/produtos` | Add product |
| PUT | `/restaurantes/{idRestaurante}/produtos/{idProduto}` | Update product |
| DELETE | `/restaurantes/{idRestaurante}/produtos/{idProduto}` | Delete product |

### 💳 Payment Methods — `/formas-pagamento`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/formas-pagamento` | List |
| POST | `/formas-pagamento` | Create |
| GET | `/formas-pagamento/{id}` | Get |
| DELETE | `/formas-pagamento/{id}` | Delete |

### 👤 Users — `/usuarios`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/usuarios` | List users |
| POST | `/usuarios` | Create user |
| GET | `/usuarios/{id}` | Get by ID |
| DELETE | `/usuarios/{id}` | Delete |
| PUT | `/usuarios/{usuarioId}/{grupoId}` | Associate user with group |
| DELETE | `/usuarios/{usuarioId}/{grupoId}` | Disassociate user from group |

### 🔐 Permissions — `/permissoes`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/permissoes` | List |
| POST | `/permissoes` | Create |
| GET | `/permissoes/{id}` | Get |
| PUT | `/permissoes/{id}` | Update |
| DELETE | `/permissoes/{id}` | Delete |

### 👥 Groups — `/grupos`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/grupos` | List |
| POST | `/grupos` | Create |
| GET | `/grupos/{id}` | Get |
| PUT | `/grupos/{id}` | Update |
| DELETE | `/grupos/{id}` | Delete |
| POST | `/grupos/{grupoId}/permissoes/{permissaoId}` | Add permission |
| DELETE | `/grupos/{grupoId}/permissoes/{permissaoId}` | Remove permission |

### 🛒 Orders — `/pedidos`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/pedidos` | Create order |
| GET | `/pedidos/{pedidoId}` | Get by ID |
| GET | `/pedidos/cliente/{clienteId}` | Orders by client |
| GET | `/pedidos/restaurante/{restauranteId}` | Orders by restaurant |
| GET | `/pedidos/filtros` | Filter (clienteId, restauranteId, status) with pagination |
| PATCH | `/pedidos/{pedidoId}` | Partial update |
| DELETE | `/pedidos/{pedidoId}` | Delete |
| PUT | `/pedidos/{pedidoId}/confirmar` | Confirm (CRIADO → CONFIRMADO) |
| PUT | `/pedidos/{pedidoId}/cancelar` | Cancel order |
| PATCH | `/pedidos/{pedidoId}/status?status={status}` | Change status |

### 📊 Statistics — `/estatisticas`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/estatisticas` | Generate statistics |
| GET | `/estatisticas` | Query statistics |

---

## 🚨 Exception Handling

### Custom Exceptions (`domain/exceptions/`)
- `BusinessException` — Base for business rule violations
- `KitchenNotFoundException`, `RestaurantNotFoundException`
- `CityNotFoundException`, `StateNotFoundException`
- `UserNotFoundException`, `OrderNotFoundException`
- `ProductNotFoundException`, `PaymentMethodNotFoundException`
- `GroupPermissionNotFoundException`, `PermissionNotFoundException`
- `InvalidStateReferenceException`

### Global Handler — `ApiExceptionHandler`
Annotated with `@RestControllerAdvice`, extends `ResponseEntityExceptionHandler`, and converts exceptions into standardized HTTP responses:

| Exception | Status |
|-----------|--------|
| `*NotFoundException` | 404 Not Found |
| `DataIntegrityViolationException` | 409 Conflict |
| `MethodArgumentNotValidException` | 400 Bad Request (with field details) |
| `HttpMessageNotReadableException` | 400 Bad Request |
| `InvalidFormatException` | 400 Bad Request |
| `PropertyBindingException` | 400 Bad Request |
| `MethodArgumentTypeMismatchException` | 400 Bad Request |

### Error response shape (`ErrorResponse`)
```json
{
  "timestamp": "2026-05-01T10:00:00-03:00",
  "status": 400,
  "title": "Invalid data",
  "message": "One or more fields are invalid",
  "fields": { "nome": "must not be blank" }
}
```

---

## ✅ Validation

- **Bean Validation** (Jakarta): `@NotBlank`, `@NotNull`, `@Valid`, `@PositiveOrZero`
- **Validation Groups** (`infra/validation/Groups.java`): `Groups.CozinhaId` for conditional validation with `@ConvertGroup`
- **Cascading validation** with `@Valid` on nested fields
- **Strict Jackson**: `FAIL_ON_UNKNOWN_PROPERTIES=true` rejects unknown JSON fields

---

## 🗄 Database Migrations (Flyway)

| Version | Description |
|---------|-------------|
| **V001** | Initial schema: kitchens, states, cities, restaurants, products, payment methods, users, groups, permissions, and join tables |
| **V002** | Restaurant flags and timestamps (active, open, dataCadastro, dataAtualizacao), embedded address, product description |
| **V003** | `tb_pedidos` and `tb_itens_pedido` tables |
| **V004** | Product FK on order items + delivery address on orders |
| **V005** | Restaurant FK on orders |
| **V006** | Restaurant timestamps converted to `TIMESTAMP WITH TIME ZONE` |
| **V007** | `tb_restaurante_responsaveis` table (restaurant owners) |
| **V008** | Product images table (`tb_produto_imagens`) |

---

## ▶️ How to Run

### Prerequisites
- Java 21
- Maven 3.9+
- Docker (for PostgreSQL)

### 1. Start the database
```bash
docker-compose up -d
```

### 2. Run the application
```bash
./mvnw spring-boot:run
```
Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

### 3. Access
The API will be available at `http://localhost:8081`.

### Configuration
- Database: PostgreSQL on `localhost:5432`, database `vifood`
- HikariCP pool: max 5, min idle 3
- DDL: `validate` (Hibernate only validates; Flyway handles schema)

---

## 🧪 Tests

| Class | Type | What it tests |
|-------|------|---------------|
| `VifoodApplicationTests` | Smoke | Spring context loading |
| `RestaurantControllerTests` | Integration | CRUD, filters, activation/deactivation |
| `KitchenControllerTests` | Integration | CRUD, pagination |
| `KitchenServiceIT` | Integration | Service logic, transactions |

The **maven-failsafe** plugin (`v3.2.5`) runs integration tests (suffix `IT`).

Run tests:
```bash
./mvnw test
./mvnw verify    # includes integration tests
```

---

## ✨ Implemented Features

✅ Full **Restaurant** CRUD with dynamic filters (name, delivery fee range)
✅ **Open/close** and **activate/deactivate** operations (single and batch)
✅ **Menu (Products)** management per restaurant (with image upload)
✅ Complete **Order** system with a 7-state state machine
✅ **Payment Methods** management and association with restaurants
✅ Hierarchical **Location** management (States → Cities)
✅ Restaurant **categorization** via Kitchens
✅ **Users, Groups, and Permissions** system
✅ Restaurant **owners (managers)**
✅ **Pagination** for kitchens and orders listings
✅ **Dynamic filters** via JPA Specifications
✅ **Validation** with Bean Validation + groups
✅ **Global exception handling** with localized messages
✅ **Versioned migrations** via Flyway (8 migrations)
✅ **PDF generation** (Flying Saucer + Thymeleaf)
✅ **Statistics** and reporting

---

## 👩‍💻 Author

**Vitória Carolina Dantas**
Project developed as part of the [AlgaWorks](https://www.algaworks.com) Spring Boot specialization course.

📅 Last updated: May/2026
