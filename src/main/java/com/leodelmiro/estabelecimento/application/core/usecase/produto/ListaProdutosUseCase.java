package com.leodelmiro.estabelecimento.application.core.usecase.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.produto.ListaProdutosInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.produto.ListaProdutosOutputPort;

import java.util.Set;

public class ListaProdutosUseCase implements ListaProdutosInputPort {
    private final ListaProdutosOutputPort listaProdutosOutputPort;

    public ListaProdutosUseCase(ListaProdutosOutputPort listaProdutosOutputPort) {
        this.listaProdutosOutputPort = listaProdutosOutputPort;
    }

    @Override
    public Set<Produto> listarTodos() {
        return listaProdutosOutputPort.listarTodos();
    }

    @Override
    public Set<Produto> listarPorLanches() {
        return listaProdutosOutputPort.listarPorLanches();
    }

    @Override
    public Set<Produto> listarPorAcompanhamentos() {
        return listaProdutosOutputPort.listarPorAcompanhamentos();
    }

    @Override
    public Set<Produto> listarPorBebidas() {
        return listaProdutosOutputPort.listarPorBebidas();
    }

    @Override
    public Set<Produto> listarPorSobremesas() {
        return listaProdutosOutputPort.listarPorSobremesas();
    }
}
