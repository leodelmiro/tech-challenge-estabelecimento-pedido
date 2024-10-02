package com.leodelmiro.estabelecimento.core.usecase.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

public interface FecharPedidoUseCase {
    Pedido fechar(Long idPedido);
}
