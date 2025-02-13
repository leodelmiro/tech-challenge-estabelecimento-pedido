package com.leodelmiro.pedido.core.usecase.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface RemoveProdutoPedidoUseCase {
    Pedido remover(Long idPedido, Long idProduto, int quantidade);
}
