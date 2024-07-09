package com.leodelmiro.estabelecimento.adapters.in.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        String descricao,
        LocalDateTime criadoEm,
        Set<ImagemResponse> imagens
) {
}
