package com.leodelmiro.estabelecimento.dataprovider.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leodelmiro.estabelecimento.core.domain.ItemPedido;

public class ItemPedidoClientRequest {
    @JsonProperty("sku_number")
    String skuNumber;
    @JsonProperty("category")
    String category;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("unit_price")
    Double unitPrice;
    @JsonProperty("quantity")
    Integer quantity;
    @JsonProperty("unit_measure")
    String unitMeasure;
    @JsonProperty("total_amount")
    Double totalAmount;

    ItemPedidoClientRequest(ItemPedido itemPedido) {
        this.skuNumber = "ITEM" + itemPedido.getId().toString() + "PROD" + itemPedido.getProduto().getId().toString();
        this.category = itemPedido.getProduto().getCategoria().toString();
        this.title = itemPedido.getProduto().getNome();
        this.description = itemPedido.getProduto().getDescricao();
        this.unitPrice = itemPedido.getProduto().getPreco().doubleValue();
        this.quantity = itemPedido.getQuantidade();
        this.unitMeasure = "unit";
        this.totalAmount = itemPedido.valorTotal().doubleValue();
    }
}
