package com.leodelmiro.estabelecimento.core.usecase.produto.impl;

import com.leodelmiro.estabelecimento.core.dataprovider.produto.RemoveProdutoGateway;
import com.leodelmiro.estabelecimento.core.usecase.produto.RemoveProdutoUseCase;

public class RemoveProdutoUseCaseImpl implements RemoveProdutoUseCase {

    private final RemoveProdutoGateway removeProdutoGateway;

    public RemoveProdutoUseCaseImpl(RemoveProdutoGateway removeProdutoGateway) {
        this.removeProdutoGateway = removeProdutoGateway;
    }

    @Override
    public void remover(Long id) {
        removeProdutoGateway.remover(id);
    }
}
