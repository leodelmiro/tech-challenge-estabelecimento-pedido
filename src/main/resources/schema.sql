--psql -U postgres

CREATE DATABASE estabelecimento;
-- \c estabelecimento;
-- DROP TABLE tb_imagem;

CREATE TABLE tb_produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    categoria INTEGER NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    descricao TEXT,
    tempo_de_preparo_em_segundos BIGINT NOT NULL,
    criado_em TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE tb_imagem (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    produto_id INTEGER NOT NULL,
    url TEXT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES tb_produto(id) ON DELETE CASCADE,
    criado_em TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE tb_cliente (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(30) NOT NULL UNIQUE,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    criado_em TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE tb_pedido (
    id SERIAL PRIMARY KEY,
    cliente_id BIGINT,
    preco_total DECIMAL(10, 2) DEFAULT 0,
    status INTEGER NOT NULL,
    tempo_total_de_preparo_em_segundos BIGINT,
    pago_em TIMESTAMP,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id)
);

CREATE TABLE tb_pedido_produto (
    pedido_id BIGINT,
    produto_id BIGINT,
    PRIMARY KEY (pedido_id, produto_id),
    FOREIGN KEY (pedido_id) REFERENCES tb_pedido(id),
    FOREIGN KEY (produto_id) REFERENCES tb_produto(id)
);