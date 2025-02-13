package com.leodelmiro.pedido.entrypoint.api.request;

public record ItemPedidoRequest(
        Long produtoId,
        int quantidade
) {
}
