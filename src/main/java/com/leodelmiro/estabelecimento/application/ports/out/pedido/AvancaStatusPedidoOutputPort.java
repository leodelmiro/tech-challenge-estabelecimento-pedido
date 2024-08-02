package com.leodelmiro.estabelecimento.application.ports.out.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

public interface AvancaStatusPedidoOutputPort {
    Pedido avancar(Pedido pedido);
}
