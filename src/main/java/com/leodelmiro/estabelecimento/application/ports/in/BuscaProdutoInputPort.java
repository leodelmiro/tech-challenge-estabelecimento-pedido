package com.leodelmiro.estabelecimento.application.ports.in;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface BuscaProdutoInputPort {
    Produto buscar(Long id);
}
