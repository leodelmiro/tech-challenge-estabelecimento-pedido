package com.leodelmiro.pedido.entrypoint.api.response;

public record ItemPedidoResponse(
        Long id,
        Long produtoId,
        int quantidade
) {
}
