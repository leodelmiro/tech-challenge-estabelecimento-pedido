package com.leodelmiro.estabelecimento.dataprovider.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leodelmiro.estabelecimento.core.domain.Pedido;

import java.util.List;

public class PedidoClientRequest {
    @JsonProperty("external_reference")
    String externalReference;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("total_amount")
    double totalAmount;
    @JsonProperty("items")
    List<ItemPedidoClientRequest> items;

    public PedidoClientRequest(Pedido pedido) {
        this.externalReference = pedido.getId().toString();
        this.title = "Estabelecimento Fiap";
        this.description = "Pedido #" + pedido.getId() + "- Estabelecimento Fiap";
        this.totalAmount = pedido.getPrecoTotal().doubleValue();
        this.items = pedido.getItens().stream().map(ItemPedidoClientRequest::new).toList();
    }

}
