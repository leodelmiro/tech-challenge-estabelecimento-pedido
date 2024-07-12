package com.leodelmiro.estabelecimento.application.ports.in.pedido;


import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

public interface IniciaPedidoInputPort {
    Pedido iniciar(String cpf);
}
