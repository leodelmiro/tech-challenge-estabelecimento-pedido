package com.leodelmiro.estabelecimento.core.usecase.pedido.impl;

import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.ListaPedidosGateway;
import com.leodelmiro.estabelecimento.core.usecase.pedido.ListaPedidosUseCase;

import java.util.Set;

public class ListaPedidosUseCaseImpl implements ListaPedidosUseCase {

    private final ListaPedidosGateway listaPedidosGateway;

    public ListaPedidosUseCaseImpl(ListaPedidosGateway listaPedidosGateway) {
        this.listaPedidosGateway = listaPedidosGateway;
    }

    @Override
    public Set<Pedido> buscar() {
        return listaPedidosGateway.listarTodos();
    }

    @Override
    public Set<Pedido> listarPedidosNaFila() {
        return listaPedidosGateway.listarPedidosNaFila();
    }
}