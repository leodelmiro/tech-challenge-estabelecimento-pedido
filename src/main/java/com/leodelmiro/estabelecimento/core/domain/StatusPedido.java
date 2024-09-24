package com.leodelmiro.estabelecimento.core.domain;

public enum StatusPedido {
    PENDENTE_FECHAMENTO(1),
    AGUARDANDO_PAGAMENTO(2),
    PAGO(3),
    RECEBIDO_ESTABELECIMENTO(4),
    EM_PREPARACAO(5),
    PRONTO(6),
    RECEBIDO_CLIENTE(7),
    FINALIZADO(8);

    private final int valor;

    StatusPedido(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public StatusPedido next() {
        return values()[(this.ordinal() + 1)];
    }
}
