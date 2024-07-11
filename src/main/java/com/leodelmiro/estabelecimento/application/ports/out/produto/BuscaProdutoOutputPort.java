package com.leodelmiro.estabelecimento.application.ports.out.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface BuscaProdutoOutputPort {
    Produto buscar(Long id);
}
