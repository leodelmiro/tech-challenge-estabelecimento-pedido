package com.leodelmiro.estabelecimento.adapters.in.controller.response;

public record ItemPedidoResponse(
        Long id,
        ProdutoResponse produto,
        int quantidade
) {
}
