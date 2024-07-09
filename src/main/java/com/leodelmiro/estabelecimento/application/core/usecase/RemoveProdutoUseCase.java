package com.leodelmiro.estabelecimento.application.core.usecase;

import com.leodelmiro.estabelecimento.application.ports.in.RemoveProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.RemoveProdutoOutputPort;

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
