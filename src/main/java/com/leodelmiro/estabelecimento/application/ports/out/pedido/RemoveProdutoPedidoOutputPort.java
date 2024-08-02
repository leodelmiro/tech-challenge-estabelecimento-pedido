package com.leodelmiro.estabelecimento.application.ports.out.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

public interface RemoveProdutoPedidoOutputPort {
    Pedido remover(Pedido pedidoSemProduto);
}
