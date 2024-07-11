package com.leodelmiro.estabelecimento.adapters.in.controller.response;

import java.time.LocalDateTime;

public record ClienteResponse(
        Long id,
        String cpf,
        String nome,
        String email,
        LocalDateTime criadoEm
) {
}
