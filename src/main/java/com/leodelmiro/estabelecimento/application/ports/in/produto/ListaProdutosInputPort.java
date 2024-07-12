package com.leodelmiro.estabelecimento.application.ports.in.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

import java.util.Set;

public interface ListaProdutosInputPort {
    Set<Produto> listarTodos();
    Set<Produto> listarPorLanches();
    Set<Produto> listarPorAcompanhamentos();
    Set<Produto> listarPorBebidas();
    Set<Produto> listarPorSobremesas();
}
