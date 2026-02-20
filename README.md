# 🛒 API GraphQL de Gerenciamento de Vendas – Classic Models

API GraphQL com **Spring Boot** + **Spring GraphQL** para gerenciamento de **vendas** (entidades inspiradas no banco Classic Models: Offices, Orders, OrderDetails/OrderProducts, etc.).  
Foco em simplicidade, resolução de relacionamentos e exposição flexível via GraphQL.

Ideal para aprendizado, protótipos rápidos ou demonstração de GraphQL em portfólio/entrevistas.

---

## 📋 Índice

- [Sobre o projeto](#-sobre-o-projeto)
- [Por que GraphQL?](#-por-que-graphql)
- [Tecnologias](#-tecnologias)
- [Funcionalidades principais](#-funcionalidades-principais)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução](#-instalação-e-execução)
- [Schema GraphQL](#-schema-graphql)
- [Exemplos de Queries e Mutations](#-exemplos-de-queries-e-mutations)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Decisões & Aprendizados](#-decisões--aprendizados)
- [Playground / Interface GraphQL](#-playground--interface-graphql)
- [Licença](#-licença)

---

## 🚀 Sobre o projeto

Backend GraphQL simples para gerenciar entidades do clássico modelo de vendas de miniaturas de carros (**Classic Models**).  
Principais entidades expostas:

- **Offices** (filiais)
- **OrderProduct** / **OrderDetails** (itens de pedidos – chave composta: orderId + productCode)

Inclui resolução automática de relacionamentos bidirecionais (Order ↔ OrderProducts, Product ↔ OrderProducts).

---

## ⚡ Por que GraphQL?

| Abordagem     | Over-fetching | Under-fetching | Flexibilidade do cliente | Complexidade no backend |
|---------------|---------------|----------------|---------------------------|--------------------------|
| REST múltiplos endpoints | Alto         | Possível      | Baixa                     | Média                    |
| GraphQL       | Evitado       | Evitado       | Alta                      | Média-Alta (resolvers)   |

Vantagens neste projeto:

- Cliente solicita exatamente os campos desejados
- Resolução lazy de relacionamentos (evita N+1 quando bem configurado)
- Único endpoint `/graphql`

---

## 🛠 Tecnologias

| Tecnologia          | Versão     | Finalidade principal                              |
|---------------------|------------|---------------------------------------------------|
| Java                | 17+        | Linguagem                                         |
| Spring Boot         | 3.x        | Framework principal                               |
| Spring GraphQL      | 1.x        | Integração GraphQL nativa                         |
| Spring Data JPA     | 3.x        | Persistência                                      |
| PostgreSQL / MySQL  | 15+ / 8+   | Banco de dados (compatível com Classic Models)    |
| GraphiQL / Altair   | —          | Interface interativa de testes                    |
| Docker + Compose    | —          | Opcional – containerização                        |

**Sem** Spring Security, Redis, Resilience4j, cursor pagination ou tratamento avançado de erros.

---

## ✨ Funcionalidades principais

- CRUD completo para **Offices** e **OrderProducts** via mutations
- Consulta de listas e entidades individuais via queries
- Resolução automática de relacionamentos (Order → OrderProducts, Product → OrderProducts)
- Chave composta tratada corretamente em OrderProduct (orderId + productCode)
- Interface GraphQL interativa (GraphiQL)

---

## 📦 Pré-requisitos

- Java 17+
- Maven 3.9+
- Docker + Docker Compose (opcional)

---

## 🚀 Instalação e Execução

### Com Docker Compose (recomendado – se houver)

```bash
git clone https://github.com/costtina/projectgraphQL.git
cd graphql-classicmodels
docker compose up -d --build

### Sem Docker

```bash
# Clone o repositório
git clone https://github.com/costtinha/projectgraphQL.git
cd projectgraphQL

# Configure o banco de dados PostgreSQL local
# (ajuste as credenciais em src/main/resources/application.yml)

# Build e execução
./mvnw spring-boot:run
```

---

## 🔗 Schema GraphQl

### Principais tipos e operações (extraídos dos arquivos .schema.graphql):

### Queries

```bash
type Query {
  offices: [Office]
  officesById(code: ID!): Office
  orderProducts: [OrderProduct]
  orderProductById(orderId: ID!, productCode: ID!): OrderProduct
}
```



### Mutations
```bash
type Mutation {
  createOffice(input: CreateOfficeInput!): Office!
  deleteOffice(code: ID!): Office
  updateOffice(code: ID!, input: CreateOfficeInput!): Office!

  createOrderProduct(input: CreateOrderProductInput!): OrderProduct!
  deleteOrderProduct(input: OrderProductKeyInput!): OrderProduct!
  updateOrderProduct(orderId: ID!, productCode: ID!, input: UpdateOrderProduct!): OrderProduct
}
```


### Tipos principais
```bash
type Office {
  code: ID!
  city: String!
  phone: String!
  address1: String!
  address2: String
  state: String!
  country: String!
  postalCode: Int!
  territory: String!
}

type OrderProduct {
  orderProductId: OrderProductKey!
  orderId: Order!
  productCode: Product!
  qnty: Int
  priceEach: Int
}

type OrderProductKey {
  orderId: ID!
  productCode: ID!
}
```
---

## 📝 Exemplos de queries e mutations

### Listar todas as filiais

```bash
query {
  offices {
  code
  city
  country
  territory
}
}
```


**Buscar um OrderProduct específico:**
```bash
query {
  orderProductById(orderId: 10100, productCode: "S10_1678") {
  qnty
  priceEach
  orderId { orderNumber status }
productCode { productName }
}
}
```
### Criar OrderProduct

```bash
 mutation {
  createOrderProduct(input: {
    orderId: 10123
    productCode: "S18_3259"
    qnty: 14
    priceEach: 87.06
  }) {
    qnty
    priceEach
  }
}
```

### Atualizar quantidade e preço
```bash
mutation {
  updateOrderProduct(orderId: 10123, productCode: "S18_3259", input: {
    qnty: 20
    priceEach: 92.50
  }) {
    qnty
    priceEach
  }
}
```

---

## 📁 Estrutura do Projeto

```
src/main/java/com/graphql/project
├── config/               # RepositoryConfig
├── controller/           # OfficeController, OrderProductController
├── dtos/                 # CreateOffice, CreateOrderProduct, UpdateOrderProduct, OrderProductKeyInput
├── entity/               # Office, OrderProduct, OrderProductKey (composite)
├── persistance/          # OfficeRepository, OrderProductRepository
├── service/
│   ├── officeService/    # OfficeService (CRUD + cursor simples)
│   └── orderProductService/ # OrderProductService (CRUD + cursor composto)
└── ProjectApplication.java  # Classe principal
```


---

## 📚 Decisões e Aprendizados

Este projeto foi desenvolvido como estudo prático dos seguintes conceitos:

Uso do Spring GraphQL nativo (sem graphql-java-tools ou graphql-kickstart)
Resolução de relacionamentos via @SchemaMapping (evita N+1 manual em muitos casos)
Tratamento simples de chave composta em OrderProduct
Foco em simplicidade: sem autenticação, cache, rate limiting ou paginação avançada
GraphQL facilita evolução do schema sem quebrar clientes antigos

---

## 📄 Licença

Este projeto é de uso educacional e está disponível sob a licença [MIT](LICENSE).

---

<p align="center">
  Desenvolvido por <a href="https://github.com/costtinha">Daniel Costa</a>
</p>
