package com.leodelmiro.estabelecimento.core.usecase.pedido;

import java.time.LocalDateTime;

public interface PagaPedidoUseCase {
    void pagar(Long idPedido, LocalDateTime pagoEm);
}
