package com.leodelmiro.estabelecimento.application.ports.in.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

import java.util.Set;

public interface BuscaProdutosInputPort {
    Set<Produto> buscarTodos();
    Set<Produto> buscarPorLanches();
    Set<Produto> buscarPorAcompanhamentos();
    Set<Produto> buscarPorBebidas();
    Set<Produto> buscarPorSobremesas();
}
