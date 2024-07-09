package com.leodelmiro.estabelecimento.application.ports.in;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface CriaProdutoInputPort {
    Produto criar(Produto produto);
}
