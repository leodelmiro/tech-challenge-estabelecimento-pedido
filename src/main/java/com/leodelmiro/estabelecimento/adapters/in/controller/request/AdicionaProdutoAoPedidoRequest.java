package com.leodelmiro.estabelecimento.adapters.in.controller.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AdicionaProdutoAoPedidoRequest(
        @NotNull List<ItemPedidoRequest> itens
) {
}
