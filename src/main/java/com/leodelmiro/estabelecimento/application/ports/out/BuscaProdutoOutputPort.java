package com.leodelmiro.estabelecimento.application.ports.out;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

import java.util.Optional;

public interface BuscaProdutoOutputPort {
    Produto busca(Long id);
}
