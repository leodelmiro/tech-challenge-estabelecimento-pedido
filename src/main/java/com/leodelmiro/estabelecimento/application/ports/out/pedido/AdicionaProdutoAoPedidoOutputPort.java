package com.leodelmiro.estabelecimento.application.ports.out.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

public interface AdicionaProdutoAoPedidoOutputPort {
    Pedido adicionar(Pedido pedido);
}
