package com.leodelmiro.pedido.core.usecase.produto.impl;

import com.leodelmiro.pedido.core.dataprovider.produto.BuscaProdutoGateway;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;

public class BuscaProdutoUseCaseImpl implements BuscaProdutoUseCase {
    private final BuscaProdutoGateway buscaProdutoGateway;

    public BuscaProdutoUseCaseImpl(BuscaProdutoGateway buscaProdutoGateway) {
        this.buscaProdutoGateway = buscaProdutoGateway;
    }

    @Override
    public Produto buscar(Long id) {
        return buscaProdutoGateway.buscar(id);
    }
}
