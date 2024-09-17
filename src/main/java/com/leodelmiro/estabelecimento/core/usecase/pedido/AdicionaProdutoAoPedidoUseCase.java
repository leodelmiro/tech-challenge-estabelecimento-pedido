package com.leodelmiro.estabelecimento.core.usecase.pedido;

import com.leodelmiro.estabelecimento.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.core.domain.Pedido;

import java.util.List;

public interface AdicionaProdutoAoPedidoUseCase {
    Pedido adicionar(Long idPedido, List<ItemPedido> itens);
}
