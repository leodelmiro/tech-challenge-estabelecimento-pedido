package com.leodelmiro.estabelecimento.application.ports.out;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

public interface EditaProdutoOutputPort {
    Produto editar(Produto produto, Long id);
}
