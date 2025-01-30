CREATE TABLE IF NOT EXISTS tb_pedido (
    id SERIAL PRIMARY KEY,
    cliente_id TEXT,
    preco_total DECIMAL(10, 2) DEFAULT 0,
    status INTEGER NOT NULL,
    tempo_total_de_preparo_em_segundos INTEGER,
    pagamento_id TEXT,
    pago_em TIMESTAMP,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_item_pedido (
    id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    quantidade INTEGER  NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);