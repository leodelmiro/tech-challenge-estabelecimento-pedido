package com.leodelmiro.estabelecimento.core.usecase.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

import java.time.LocalDateTime;

public interface PagaPedidoUseCase {
    Pedido pagar(Long idPedido, LocalDateTime pagoEm);
}
