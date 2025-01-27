package com.leodelmiro.pedido.entrypoint.api.request;

import jakarta.validation.constraints.NotBlank;

public record CadastraProdutoImagemRequest(
        @NotBlank String nome,
        @NotBlank String url
) {
}
