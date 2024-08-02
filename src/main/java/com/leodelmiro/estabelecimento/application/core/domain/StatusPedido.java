package com.leodelmiro.estabelecimento.application.core.domain;

public enum StatusPedido {
    PENDENTE_FECHAMENTO(1),
    AGUARDANDO_PAGAMENTO(2),
    PAGO(3),
    RECEBIDO(4),
    EM_PREPARACAO(5),
    PRONTO(6),
    FINALIZADO(7);

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
