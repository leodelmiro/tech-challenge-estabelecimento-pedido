package com.leodelmiro.pedido.core.dataprovider.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

import java.util.Set;

public interface ListaPedidosGateway {
    Set<Pedido> listarTodos();

    Set<Pedido> listarPedidosNaFila();
}
