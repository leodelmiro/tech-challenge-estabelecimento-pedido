package com.leodelmiro.estabelecimento.application.core.usecase.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.produto.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.produto.BuscaProdutoOutputPort;

public class BuscaProdutoUseCase implements BuscaProdutoInputPort {
    private final BuscaProdutoOutputPort buscaProdutoOutputPort;

    public BuscaProdutoUseCase(BuscaProdutoOutputPort buscaProdutoOutputPort) {
        this.buscaProdutoOutputPort = buscaProdutoOutputPort;
    }

    @Override
    public Produto buscar(Long id) {
        return buscaProdutoOutputPort.buscar(id);
    }
}
