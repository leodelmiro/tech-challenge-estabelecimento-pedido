package com.leodelmiro.estabelecimento.application.core.usecase;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.CriaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.CriaProdutoOutputPort;

public class CriaProdutoUseCase implements CriaProdutoInputPort {

    private final CriaProdutoOutputPort criaProdutoOutputPort;

    public CriaProdutoUseCase(CriaProdutoOutputPort criaProdutoOutputPort) {
        this.criaProdutoOutputPort = criaProdutoOutputPort;
    }

    @Override
    public Produto criar(Produto produto) {
        return criaProdutoOutputPort.criar(produto);
    }
}
