package com.leodelmiro.pedido.core.usecase.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface FecharPedidoUseCase {
    Pedido fechar(Long idPedido);
}
