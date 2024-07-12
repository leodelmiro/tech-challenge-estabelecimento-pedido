package com.leodelmiro.estabelecimento.application.ports.out.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

import java.util.Set;

public interface ListaPedidosOutputPort {
    Set<Pedido> listarTodos();

    Set<Pedido> listarPedidosNaFila();
}
