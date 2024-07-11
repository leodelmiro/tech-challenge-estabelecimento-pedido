package com.leodelmiro.estabelecimento.application.ports.in.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface CadastraProdutoInputPort {
    Produto cadastrar(Produto produto);
}
