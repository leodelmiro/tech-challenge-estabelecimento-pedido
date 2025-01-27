package com.leodelmiro.pedido.core.dataprovider.pedido;


import com.leodelmiro.pedido.core.domain.Pedido;

public interface IniciaPedidoGateway {
    Pedido iniciar(Pedido pedido);
}
