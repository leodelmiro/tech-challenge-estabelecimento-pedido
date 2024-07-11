package com.leodelmiro.estabelecimento.application.core.usecase.produto;

import com.leodelmiro.estabelecimento.application.ports.in.produto.RemoveProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.produto.RemoveProdutoOutputPort;

public class RemoveProdutoUseCase implements RemoveProdutoInputPort {

    private final RemoveProdutoOutputPort removeProdutoOutputPort;

    public RemoveProdutoUseCase(RemoveProdutoOutputPort removeProdutoOutputPort) {
        this.removeProdutoOutputPort = removeProdutoOutputPort;
    }

    @Override
    public void remover(Long id) {
        removeProdutoOutputPort.remover(id);
    }
}
