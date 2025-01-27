package com.leodelmiro.pedido.core.dataprovider.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface PagaPedidoGateway {
    void pagar(Pedido pedido);
}
