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