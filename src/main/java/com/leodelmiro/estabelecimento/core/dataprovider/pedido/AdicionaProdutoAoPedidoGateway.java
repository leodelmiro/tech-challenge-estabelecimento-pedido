package com.leodelmiro.estabelecimento.core.dataprovider.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

public interface AdicionaProdutoAoPedidoGateway {
    Pedido adicionar(Pedido pedido);
}
