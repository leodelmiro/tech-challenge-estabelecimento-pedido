package com.leodelmiro.estabelecimento.core.usecase.produto;

import com.leodelmiro.estabelecimento.core.domain.Produto;

public interface BuscaProdutoUseCase {
    Produto buscar(Long id);
}
