package com.leodelmiro.pedido.dataprovider.client.request;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Produto;

public record ItemPedidoRequest(
        Long id_item_pedido,
        Long id_produto,
        String categoria,
        String nome,
        String descricao,
        Double preco_unitario,
        Integer quantidade,
        Double preco_total
) {
    public ItemPedidoRequest(ItemPedido itemPedido, Produto produto) {
        this(
                itemPedido.getId(),
                itemPedido.getProdutoId(),
                produto.getCategoria().name(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco().doubleValue(),
                itemPedido.getQuantidade(),
                itemPedido.valorTotal(produto).doubleValue()
        );
    }
}
