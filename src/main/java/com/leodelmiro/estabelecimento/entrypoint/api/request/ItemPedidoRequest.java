package com.leodelmiro.estabelecimento.entrypoint.api.request;

public record ItemPedidoRequest(
        Long idProduto,
        int quantidade
) {
}
