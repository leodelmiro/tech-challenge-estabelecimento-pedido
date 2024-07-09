package com.leodelmiro.estabelecimento.application.core.usecase;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.BuscaProdutosInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.BuscaProdutoOutputPort;
import com.leodelmiro.estabelecimento.application.ports.out.BuscaProdutosOutputPort;

import java.util.Set;

public class BuscaProdutosUseCase implements BuscaProdutosInputPort {
    private final BuscaProdutosOutputPort buscaProdutosOutputPort;

    public BuscaProdutosUseCase(BuscaProdutosOutputPort buscaProdutosOutputPort) {
        this.buscaProdutosOutputPort = buscaProdutosOutputPort;
    }

    @Override
    public Set<Produto> buscarTodos() {
        return buscaProdutosOutputPort.buscarTodos();
    }

    @Override
    public Set<Produto> buscarPorLanches() {
        return buscaProdutosOutputPort.buscarPorLanches();
    }

    @Override
    public Set<Produto> buscarPorAcompanhamentos() {
        return buscaProdutosOutputPort.buscarPorAcompanhamentos();
    }

    @Override
    public Set<Produto> buscarPorBebidas() {
        return buscaProdutosOutputPort.buscarPorBebidas();
    }

    @Override
    public Set<Produto> buscarPorSobremesas() {
        return buscaProdutosOutputPort.buscarPorSobremesas();
    }
}
