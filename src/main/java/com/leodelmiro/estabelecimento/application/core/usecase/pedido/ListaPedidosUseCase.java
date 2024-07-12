package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.ListaPedidosInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.ListaPedidosOutputPort;

import java.util.Set;

public class ListaPedidosUseCase implements ListaPedidosInputPort {

    private final ListaPedidosOutputPort listaPedidosOutputPort;

    public ListaPedidosUseCase(ListaPedidosOutputPort listaPedidosOutputPort) {
        this.listaPedidosOutputPort = listaPedidosOutputPort;
    }

    @Override
    public Set<Pedido> listarTodos() {
        return listaPedidosOutputPort.listarTodos();
    }

    @Override
    public Set<Pedido> listarPedidosNaFila() {
        return listaPedidosOutputPort.listarPedidosNaFila();
    }
}