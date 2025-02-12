package com.leodelmiro.pedido.dataprovider.client.response;

import com.leodelmiro.pedido.core.domain.Categoria;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        Categoria categoria,
        BigDecimal preco,
        String descricao,
        Long tempoDePreparoEmSegundos
) {
}
