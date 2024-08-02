package com.leodelmiro.estabelecimento.application.ports.in.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

public interface RemoveProdutoPedidoInputPort {
    Pedido remover(Long idPedido, Long idProduto, int quantidade);
}
