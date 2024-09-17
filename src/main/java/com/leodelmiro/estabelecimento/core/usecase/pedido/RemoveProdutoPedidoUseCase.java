package com.leodelmiro.estabelecimento.core.usecase.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

public interface RemoveProdutoPedidoUseCase {
    Pedido remover(Long idPedido, Long idProduto, int quantidade);
}
