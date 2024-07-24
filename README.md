# Microserviço de Pedidos com RabbitMQ e MongoDB

Este projeto é um microserviço desenvolvido em Spring Boot que envia e consome dados de uma fila RabbitMQ e grava esses dados em um banco de dados MongoDB. O serviço permite listar as seguintes informações:

- Valor total do pedido.
- Quantidade de pedidos por cliente.
- Lista de pedidos realizados por cliente.

## Estrutura da Mensagem Consumida

O microserviço consome mensagens da fila RabbitMQ com a seguinte estrutura:

```json
{
  "orderId": 1001,
  "customerId": 1,
  "items": [
    {
      "product": "lápis",
      "quantity": 100,
      "price": 1.1
    },
    {
      "product": "caderno",
      "quantity": 10,
      "price": 1.0
    }
  ]
}
```

## Funcionalidades da API REST

A API REST permite consultar as seguintes informações:

1. **Enviar o pedido**:
   - Endpoint: `/orders`
   - Método: `POST`
   - Descrição: Envia o pedido para a fila do RabbitMQ
2. **Todos os pedidos**:
   - Endpoint: `/orders`
   - Método: `GET`
3. **Lista de pedidos realizados por cliente o total dos produtos e a quantidade de pedidos**:
   - Endpoint: `/api/clientes/{customerId}/orders`
   - Método: `GET`

## Tecnologias Utilizadas

- Spring Boot
- RabbitMQ
- MongoDB
- Docker

## Configuração e Execução

### Pré-requisitos

- Docker
- Docker Compose
- Java 17 ou superior
- Maven

### Passos para Execução

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/MSpilari/microservice-mongo-rabbitmq
   cd seu-repositorio
   ```

2. **Inicie os contêineres do Docker**:

   ```bash
   docker-compose up -d
   ```

3. **Configure as variáveis de ambiente no `application.properties` do Spring Boot**:

   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/seu-banco-de-dados
   spring.rabbitmq.host=localhost
   spring.rabbitmq.port=5672
   ```

4. **Construa e execute a aplicação Spring Boot**:
   ```bash
   mvn clean install
   java -jar target/seu-projeto.jar
   ```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
