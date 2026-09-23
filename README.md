# 🛒 E-Commerce Core & Gestão de Estoque API

API RESTful robusta desenvolvida em **Java** com **Spring Boot 3** para gerenciamento centralizado de vendas, clientes, catálogo de produtos e controle transacional de estoque.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 25
* **Framework:** Spring Boot 4.1.1
* **Persistência:** Spring Data JPA / Hibernate
* **Validações:** Jakarta Bean Validation
* **Banco de Dados:** MySQL 8
* **Utilitários:** Lombok
* **Padronização de API:** Padrões REST com tratamento global de exceções (RFC 7807)

---

## 📐 Arquitetura e Estrutura de Pastas

A aplicação segue uma arquitetura em camadas bem delimitada:

```text
src/main/java/br/com/gabriel/ecommerce/
├── controller/     # Endpoints HTTP da API
├── dto/            # Records imutáveis para entrada e saída de dados (Request / Response)
├── entity/         # Mapeamentos relacionais JPA
├── enums/          # Enums de domínio (ex: StatusPedido)
├── exception/      # Exceções de negócio e Handler global (@RestControllerAdvice)
├── repository/     # Interfaces Spring Data JPA
└── service/        # Regras de negócio e controle transacional (@Transactional)
```

---

## ⚙️ Regras de Negócio Principais

1. **Controle Estrito de Estoque:** Nenhum pedido é concluído caso qualquer produto solicitado não tenha quantidade suficiente disponível.
2. **Snapshot de Preço:** O preço unitário do item é congelado no ato da compra, garantindo a integridade histórica dos pedidos mesmo após aumentos ou reduções de preço no catálogo.
3. **Transição de Estados:** O pedido obedece ao fluxo: `CRIADO` -> `PAGO` -> `ENVIADO`.
4. **Estorno Automático:** O cancelamento de pedidos reverte as quantidades reservadas de volta para o estoque dos respectivos produtos.

---

## 📌 Endpoints da API

### Categorias (`/api/v1/categorias`)
| Método | Endpoint | Descrição | Status Sucesso |
|---|---|---|---|
| `GET` | `/api/v1/categorias` | Lista todas as categorias | `200 OK` |
| `POST` | `/api/v1/categorias` | Cadastra nova categoria | `201 Created` |

### Produtos (`/api/v1/produtos`)
| Método | Endpoint | Descrição | Status Sucesso |
|---|---|---|---|
| `GET` | `/api/v1/produtos` | Lista produtos paginados | `200 OK` |
| `GET` | `/api/v1/produtos/{id}` | Busca produto por ID | `200 OK` |
| `POST` | `/api/v1/produtos` | Cadastra novo produto | `201 Created` |
| `PUT` | `/api/v1/produtos/{id}` | Atualiza dados do produto | `200 OK` |

### Clientes (`/api/v1/clientes`)
| Método | Endpoint | Descrição | Status Sucesso |
|---|---|---|---|
| `GET` | `/api/v1/clientes` | Lista clientes cadastrados | `200 OK` |
| `POST` | `/api/v1/clientes` | Cadastra novo cliente | `201 Created` |

### Pedidos (`/api/v1/pedidos`)
| Método | Endpoint | Descrição | Status Sucesso |
|---|---|---|---|
| `POST` | `/api/v1/pedidos` | Cria pedido e reserva estoque | `201 Created` |
| `GET` | `/api/v1/pedidos/{id}` | Detalhes do pedido e itens | `200 OK` |
| `PATCH` | `/api/v1/pedidos/{id}/status`| Atualiza status do pedido | `200 OK` |

---


## 👨‍💻 Autor

Desenvolvido por **Gabriel Silvestre**  
Projeto de portfólio backend focado em regras de negócio, persistência relacional e arquitetura limpa em Spring Boot.