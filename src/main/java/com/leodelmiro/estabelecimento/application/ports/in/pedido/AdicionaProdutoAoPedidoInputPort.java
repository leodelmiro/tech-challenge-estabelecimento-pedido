package com.leodelmiro.estabelecimento.application.ports.in.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;

import java.util.List;

public interface AdicionaProdutoAoPedidoInputPort {
    Pedido adicionar(Long idPedido, List<ItemPedido> itens);
}
