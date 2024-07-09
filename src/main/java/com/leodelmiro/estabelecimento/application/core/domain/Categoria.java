package com.leodelmiro.estabelecimento.application.core.domain;

public enum Categoria {
    LANCHE(1), ACOMPANHAMENTO(2), BEBIDA(3), SOBREMESA(4);

    private final int valor;

    Categoria(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
