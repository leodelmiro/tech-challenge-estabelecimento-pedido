package com.leodelmiro.estabelecimento.entrypoint.api.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AdicionaProdutoAoPedidoRequest(
        @NotNull List<ItemPedidoRequest> itens
) {
}
