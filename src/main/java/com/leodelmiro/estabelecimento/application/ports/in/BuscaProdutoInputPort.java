package com.leodelmiro.estabelecimento.application.ports.in;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

import java.util.Optional;

public interface BuscaProdutoInputPort {
    Produto busca(Long id);
}
