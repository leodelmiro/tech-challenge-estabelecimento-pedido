package com.leodelmiro.estabelecimento.adapters.in.controller.response;


import java.time.LocalDateTime;

public record ImagemResponse(
        Long id,
        String nome,
        String url,
        LocalDateTime criadoEm
) {
}
