package com.leodelmiro.estabelecimento.application.ports.in.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

import java.util.Set;

public interface ListaPedidosInputPort {
    Set<Pedido> listarTodos();
    Set<Pedido> listarPedidosNaFila();
}
