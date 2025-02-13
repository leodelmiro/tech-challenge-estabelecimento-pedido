# Tech Challenge - Estabelecimento

# Índice

* [Índice](#índice)
* [Breve Descrição](#Breve-Descrição)
* [Tecnologias Utilizadas](#Tecnologias-Utilizadas)
* [Desenho Solução](#Desenho-Solução)
* [Estrutura do Projeto](#Estrutura-do-Projeto)
* [Rodando o Projeto Local](#Rodando-o-Projeto-Local)
* [Endpoints](#Endpoints)

## Breve Descrição

Aplicação se trata de um Projeto Fiap Tech Challenge - Software Architecture, simulando um projeto de um estabelecimento
de pedidos (fast-food).

Este repositório é referente ao microserviço responsável pelos Pedidos com a Solução em Clean Architecture

### Demais repositórios
- https://github.com/leodelmiro/tech-challenge-estabelecimento-db
- https://github.com/leodelmiro/tech-challenge-estabelecimento-infra
- https://github.com/leodelmiro/tech-challenge-estabelecimento-lambda-authorizer
- https://github.com/leodelmiro/tech-challenge-estabelecimento-gtw
- https://github.com/leodelmiro/tech-challenge-estabelecimento-pagamento
- https://github.com/leodelmiro/tech-challenge-estabelecimento-cliente
- https://github.com/leodelmiro/tech-challenge-estabelecimento-produto

## Tecnologias Utilizadas

- Java
- Spring
- Swagger
- Postgres
- Docker
- Docker Compose
- AWS
- Kubernetes

## Desenho

### Componentes

- Pedidos
    - Inicia Pedido </br>
      ![Inicia Pedido](./desenhos/IniciaPedidoComponentes.png)
    - Adiciona Produto ao Pedido </br>
      ![Adiciona Produto ao Pedido](./desenhos/AdicionaProdutoAoPedidoComponentes.png)
    - Remove Produto Pedido </br>
      ![Remove Produto Pedido](./desenhos/RemoveProdutoPedidoComponentes.png)
    - Avança Pedido </br>
      ![Avança Pedido](./desenhos/AvancaPedidoComponentes.png)
    - Lista Pedidos e Lista Pedidos na Fila </br>
      ![Lista Pedidos e Lista Pedidos na Fila](./desenhos/ListaPedidosComponentes.png)

## Estrutura do Projeto

- Entrypoint: Entrada de acesso externo para a aplicação
    - Api: Entrada de acesso via Rest a aplicação
    - Controller: Controlador da lógica dos UseCases do que chega na aplicação.
    - Presenter: Camada que faz algumas transformações de dados para serem apresentadas.
- Core: Sem acesso ao mundo externo, livre de frameworks e isolado.
    - Domain: Pode ser acesso por qualquer um.
    - Usecase: Regras de negócio
    - Dataprovider: Interfaces para o dados do mundo externo
- Dataprovider: Implmentações para o mundo externo
    - Gateway: Implementações das Interfaces de acesso do Core.
    - Repository: Database
- Config: Configurações do Projeto
- Deployment:
  - Arquivos Kubernetes para deploy

## Rodando o Projeto Local

### 1. Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Docker
- Git
- JDK 21 ou superior

### 2. Clonar o Repositório

Clone o repositório do projeto:

```sh
git clone https://github.com/leodelmiro/tech-challenge-estabelecimento-app-pedido
```

### 3. Executar o Script de Setup

O projeto inclui um script de setup (`setup.sh`) que automatiza o processo de construção e execução do projeto. O script
realiza as seguintes operações:

- Para e remove os contêineres Docker, juntamente com seus volumes.
- Executa a construção do projeto Maven.
- Inicia os contêineres Docker em modo destacável e reconstrói as imagens se necessário.

Para executar o script, siga os passos abaixo:

#### macOS e Linux

1. **Tornar o Script Executável**:

    ```sh
    chmod +x setup.sh
    ```

2. **Executar o Script**:

    ```sh
    ./setup.sh
    ```

#### Windows

1. **Executar o Script**:

   No PowerShell ou Git Bash:

    ```sh
    ./setup.sh
    ```

## Endpoints

Os Endpoints da aplicação, podem ser acessados pelo Swagger ao rodar o projeto na
url http://localhost:8080/swagger-ui/index.html

**Caso preferir pode importar a collection Insomnia que se encontra no projeto, no arquivo Insomnia Collection, porém
será
necessário o Insomnia instalado.**
