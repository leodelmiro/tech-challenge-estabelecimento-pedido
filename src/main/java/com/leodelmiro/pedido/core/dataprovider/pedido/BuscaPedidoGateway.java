package com.leodelmiro.pedido.core.dataprovider.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;

public interface BuscaPedidoGateway {
    Pedido buscar(Long id);
}
