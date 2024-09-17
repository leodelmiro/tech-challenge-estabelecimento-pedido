package com.leodelmiro.estabelecimento.core.dataprovider.produto;

import com.leodelmiro.estabelecimento.core.domain.Produto;

public interface BuscaProdutoGateway {
    Produto buscar(Long id);
}
