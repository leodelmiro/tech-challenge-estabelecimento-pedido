package com.leodelmiro.estabelecimento.entrypoint.api.response;


import java.time.LocalDateTime;

public record ImagemResponse(
        Long id,
        String nome,
        String url,
        LocalDateTime criadoEm
) {
}
