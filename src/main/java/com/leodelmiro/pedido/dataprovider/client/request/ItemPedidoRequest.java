package com.leodelmiro.pedido.dataprovider.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Produto;

public record ItemPedidoRequest(
        @JsonProperty("id_item_pedido")
        Long idItemPedido,
        @JsonProperty("id_produto")
        Long idProduto,
        @JsonProperty("categoria")
        String categoria,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("descricao")
        String descricao,
        @JsonProperty("preco_unitario")
        Double precoUnitario,
        @JsonProperty("quantidade")
        Integer quantidade,
        @JsonProperty("preco_total")
        Double precoTotal
) {
    public ItemPedidoRequest(ItemPedido itemPedido) {
        this(
                itemPedido.getId(),
                itemPedido.getProduto().getId(),
                itemPedido.getProduto().getCategoria().name(),
                itemPedido.getProduto().getNome(),
                itemPedido.getProduto().getDescricao(),
                itemPedido.getProduto().getPreco().doubleValue(),
                itemPedido.getQuantidade(),
                itemPedido.valorTotal().doubleValue()
        );
    }
}
