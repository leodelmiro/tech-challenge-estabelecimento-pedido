package com.leodelmiro.estabelecimento.core.dataprovider.pedido;

import com.leodelmiro.estabelecimento.core.domain.Pedido;

public interface AvancaStatusPedidoGateway {
    Pedido avancar(Pedido pedido);
}
