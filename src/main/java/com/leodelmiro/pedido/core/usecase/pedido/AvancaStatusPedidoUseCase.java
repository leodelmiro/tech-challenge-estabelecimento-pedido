package com.leodelmiro.pedido.core.usecase.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface AvancaStatusPedidoUseCase {
    Pedido avancar(Long idPedido);
}
