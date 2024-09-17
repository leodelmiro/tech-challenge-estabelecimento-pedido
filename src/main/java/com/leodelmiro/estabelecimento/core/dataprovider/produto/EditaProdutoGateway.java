package com.leodelmiro.estabelecimento.core.dataprovider.produto;

import com.leodelmiro.estabelecimento.core.domain.Produto;

public interface EditaProdutoGateway {
    Produto editar(Produto produto, Long id);
}
