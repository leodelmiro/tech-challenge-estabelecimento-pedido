package com.leodelmiro.estabelecimento.core.dataprovider.produto;

import com.leodelmiro.estabelecimento.core.domain.Produto;

public interface CadastraProdutoGateway {
    Produto cadastrar(Produto produto);
}
