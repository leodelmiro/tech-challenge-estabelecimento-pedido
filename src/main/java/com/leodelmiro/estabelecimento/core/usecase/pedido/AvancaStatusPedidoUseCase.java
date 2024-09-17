package com.leodelmiro.estabelecimento.core.usecase.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

public interface AvancaStatusPedidoUseCase {
    Pedido avancar(Long idPedido);
}
