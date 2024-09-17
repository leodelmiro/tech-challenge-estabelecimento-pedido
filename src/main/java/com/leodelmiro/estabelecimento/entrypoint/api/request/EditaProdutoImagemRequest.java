package com.leodelmiro.estabelecimento.entrypoint.api.request;

import jakarta.validation.constraints.NotBlank;

public record EditaProdutoImagemRequest(
        @NotBlank String nome,
        @NotBlank String url
) {
}
