package com.leodelmiro.estabelecimento.application.ports.in.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface EditaProdutoInputPort {
    Produto editar(Produto produto, Long id);
}
