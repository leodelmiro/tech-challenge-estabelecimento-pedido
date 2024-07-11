package com.leodelmiro.estabelecimento.adapters.in.controller.request;

import com.leodelmiro.estabelecimento.application.core.domain.Categoria;

import java.math.BigDecimal;
import java.util.Set;

public record EditaProdutoRequest(
        String nome,
        Categoria categoria,
        BigDecimal preco,
        String descricao,
        Set<CadastraProdutoImagemRequest> imagens
) {
}
