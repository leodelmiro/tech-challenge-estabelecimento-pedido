package com.leodelmiro.estabelecimento.core.usecase.produto.impl;

import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.ListaProdutosGateway;
import com.leodelmiro.estabelecimento.core.usecase.produto.ListaProdutosUseCase;

import java.util.Set;

public class ListaProdutosUseCaseImpl implements ListaProdutosUseCase {
    private final ListaProdutosGateway listaProdutosGateway;

    public ListaProdutosUseCaseImpl(ListaProdutosGateway listaProdutosGateway) {
        this.listaProdutosGateway = listaProdutosGateway;
    }

    @Override
    public Set<Produto> listarTodos() {
        return listaProdutosGateway.listarTodos();
    }

    @Override
    public Set<Produto> listarPorLanches() {
        return listaProdutosGateway.listarPorLanches();
    }

    @Override
    public Set<Produto> listarPorAcompanhamentos() {
        return listaProdutosGateway.listarPorAcompanhamentos();
    }

    @Override
    public Set<Produto> listarPorBebidas() {
        return listaProdutosGateway.listarPorBebidas();
    }

    @Override
    public Set<Produto> listarPorSobremesas() {
        return listaProdutosGateway.listarPorSobremesas();
    }
}
