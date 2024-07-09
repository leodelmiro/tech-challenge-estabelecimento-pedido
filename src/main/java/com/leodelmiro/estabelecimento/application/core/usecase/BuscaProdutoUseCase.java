package com.leodelmiro.estabelecimento.application.core.usecase;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.BuscaProdutoOutputPort;

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
