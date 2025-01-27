package com.leodelmiro.pedido.core.usecase.produto;

import com.leodelmiro.pedido.core.domain.Produto;

public interface BuscaProdutoUseCase {
    Produto buscar(Long id);
}
