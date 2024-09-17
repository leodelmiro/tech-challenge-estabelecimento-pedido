package com.leodelmiro.estabelecimento.core.dataprovider.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

import java.util.Set;

public interface ListaPedidosGateway {
    Set<Pedido> listarTodos();

    Set<Pedido> listarPedidosNaFila();
}
