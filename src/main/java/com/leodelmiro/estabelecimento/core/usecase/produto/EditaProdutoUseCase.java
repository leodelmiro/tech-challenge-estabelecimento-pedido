package com.leodelmiro.estabelecimento.core.usecase.produto;

import com.leodelmiro.estabelecimento.core.domain.Produto;

public interface EditaProdutoUseCase {
    Produto editar(Produto produto, Long id);
}
