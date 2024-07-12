package com.leodelmiro.estabelecimento.application.ports.out.pedido;


import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

public interface IniciaPedidoOutputPort {
    Pedido iniciar(Pedido pedido);
}
