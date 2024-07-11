package com.leodelmiro.estabelecimento.application.ports.in.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface BuscaProdutoInputPort {
    Produto buscar(Long id);
}
