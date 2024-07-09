package com.leodelmiro.estabelecimento.application.core.usecase;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.BuscaProdutoOutputPort;

import java.util.Optional;

public class BuscaProdutoUseCase implements BuscaProdutoInputPort {
    private final BuscaProdutoOutputPort buscaProdutoOutputPort;

    public BuscaProdutoUseCase(BuscaProdutoOutputPort buscaProdutoOutputPort) {
        this.buscaProdutoOutputPort = buscaProdutoOutputPort;
    }

    @Override
    public Produto busca(Long id) {
        return buscaProdutoOutputPort.busca(id);
    }
}
