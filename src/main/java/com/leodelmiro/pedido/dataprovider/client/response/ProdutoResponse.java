package com.leodelmiro.pedido.dataprovider.client.response;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        String descricao,
        Long tempoDePreparoEmSegundos
) {
}
