package com.leodelmiro.estabelecimento.core.usecase.pedido.impl;

import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.BuscaPedidoGateway;
import com.leodelmiro.estabelecimento.core.usecase.pedido.BuscaPedidoUseCase;

public class BuscaPedidoUseCaseImpl implements BuscaPedidoUseCase {

    private final BuscaPedidoGateway buscaPedidoGateway;

    public BuscaPedidoUseCaseImpl(BuscaPedidoGateway buscaPedidoGateway) {
        this.buscaPedidoGateway = buscaPedidoGateway;
    }

    @Override
    public Pedido buscar(Long id) {
        return buscaPedidoGateway.buscar(id);
    }

}