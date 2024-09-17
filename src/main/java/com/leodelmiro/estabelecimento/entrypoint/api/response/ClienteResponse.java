package com.leodelmiro.estabelecimento.entrypoint.api.response;

import java.time.LocalDateTime;

public record ClienteResponse(
        Long id,
        String cpf,
        String nome,
        String email,
        LocalDateTime criadoEm
) {
}
