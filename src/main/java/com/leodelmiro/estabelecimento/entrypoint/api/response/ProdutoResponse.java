package com.leodelmiro.estabelecimento.entrypoint.api.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        String descricao,
        Long tempoDePreparoEmSegundos,
        LocalDateTime criadoEm,
        Set<ImagemResponse> imagens
) {
}
