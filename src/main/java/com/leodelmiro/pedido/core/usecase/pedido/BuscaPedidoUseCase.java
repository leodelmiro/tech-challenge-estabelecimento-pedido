package com.leodelmiro.pedido.core.usecase.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface BuscaPedidoUseCase {
    Pedido buscar(Long id);
}
