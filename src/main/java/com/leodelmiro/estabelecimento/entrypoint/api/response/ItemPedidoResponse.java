package com.leodelmiro.estabelecimento.entrypoint.api.response;

public record ItemPedidoResponse(
        Long id,
        ProdutoResponse produto,
        int quantidade
) {
}
