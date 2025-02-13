package com.leodelmiro.pedido.core.usecase.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

import java.util.Set;

public interface ListaPedidosUseCase {
    Set<Pedido> buscar();
    Set<Pedido> listarPedidosNaFila();
}
