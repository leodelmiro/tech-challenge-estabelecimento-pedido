package com.leodelmiro.pedido.core.dataprovider.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface RemoveProdutoPedidoGateway {
    Pedido remover(Pedido pedidoSemProduto);
}
