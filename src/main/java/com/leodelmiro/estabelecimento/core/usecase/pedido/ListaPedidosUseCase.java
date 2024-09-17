package com.leodelmiro.estabelecimento.core.usecase.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

import java.util.Set;

public interface ListaPedidosUseCase {
    Set<Pedido> buscar();
    Set<Pedido> listarPedidosNaFila();
}
