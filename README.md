# 🧙‍♂️ CRUD - Harry Potter

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot que permite gerenciar personagens do universo de Harry Potter. O objetivo é praticar conceitos de desenvolvimento web, arquitetura em camadas, banco de dados relacional e conteinerização com Docker.

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5**
- **Maven**
- **PostgreSQL 15**
- **Docker & Docker Compose**
- **Lombok**
- **Spring Data JPA**
- **Swagger (Springdoc OpenAPI)**

## 📦 Funcionalidades

- Cadastro de bruxos com atributos como nome e casa
- Atualização de dados de bruxos.
- Exclusão lógica de bruxos.
- Documentação interativa via Swagger UI.

## 🚀 Como executar o projeto

### Pré-requisitos

- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/install/)
- Git
- Java 8 ou superior
- Maven

### Passo a passo

1. Clone o repositório:

```bash
git clone https://github.com/guibarbian/Crud-HarryPotter.git
cd Crud-HarryPotter
````

2. Suba os containers com Docker Compose:
````bash
docker-compose up --build
````

Esse comando irá:

- Criar um container para o banco de dados PostgreSQL.

- Criar um container para a aplicação Spring Boot.

- Mapear as portas automaticamente:

    - API: http://localhost:8080

    - Swagger: http://localhost:8080/swagger-ui.html ou /swagger-ui/index.html

3. Acesse a documentação da API:

http://localhost:8080/swagger-ui.html

## Endpoints
A API possui os seguintes endpoints:

| Método | Endpoint              | Descrição               |
|--------|-----------------------|-------------------------|
| GET    | `/api/v1/bruxos`      | Retorna todos os bruxos |
| POST   | `/api/v1/bruxos`      | Cria um bruxo           |
| PUT    | `/api/v1/bruxos/{id}` | Atualiza um bruxo       |
| DELETE | `/api/v1/bruxos/{id}` | Deleta um bruxo         |

### Exemplo de uso

Para criar ou atualizar um bruxo, você deve enviar um corpo JSON com os seguintes atributos:
```json
{
  "nome": "nomeDoBruxo",
  "casa": "Sonserina ou Grifinória"
}
```


## 🐘 Banco de Dados
O banco utilizado é o PostgreSQL, configurado via Docker com as seguintes credenciais:

```` bash
POSTGRES_DB: harrypotter
POSTGRES_USER: postgres
POSTGRES_PASSWORD: postgres
````

Essas variáveis estão definidas no arquivo docker-compose.yml.

A aplicação é configurada para rodar automaticamente o schema, criando as tabelas na inicialização (spring.jpa.hibernate.ddl-auto=update).

## 🧪 Testes
Esta aplicação possui testes unitários e de integração utilizando JUnit 5 e Mockito. Para executá-los, utilize o comando:

```bash
mvn test
```

## 📘 Documentação
- A API é documentada com Springdoc OpenAPI, acessível em:

http://localhost:8080/swagger-ui.html

## 🐳 Docker
Este projeto utiliza Docker e Docker Compose para facilitar o setup:

- O Dockerfile cria uma imagem da aplicação Spring Boot.

- O docker-compose.yml orquestra os serviços da API e do PostgreSQL.

- Exemplo de uso básico já incluído com volumes persistentes e rede customizada.

Para parar os containers:

````bash
docker-compose down
````

Para remover volumes e rede:
````bash
docker-compose down -v
````

# Desenvolvido com ⚙

- **IntelliJ IDEA**

# Autor ✏

- Guilherme A. Barbian 