package com.leodelmiro.estabelecimento.application.ports.out.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;

import java.util.Set;

public interface ListaProdutosOutputPort {
    Set<Produto> listarTodos();
    Set<Produto> listarPorLanches();
    Set<Produto> listarPorAcompanhamentos();
    Set<Produto> listarPorBebidas();
    Set<Produto> listarPorSobremesas();
}
