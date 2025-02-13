package com.leodelmiro.pedido.core.dataprovider.produto;

import com.leodelmiro.pedido.core.domain.Produto;

public interface BuscaProdutoGateway {
    Produto buscar(Long id);
}
