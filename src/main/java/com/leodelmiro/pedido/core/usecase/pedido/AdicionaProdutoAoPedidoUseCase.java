package com.leodelmiro.pedido.core.usecase.pedido;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;

import java.util.List;

public interface AdicionaProdutoAoPedidoUseCase {
    Pedido adicionar(Long idPedido, List<ItemPedido> itens);
}
