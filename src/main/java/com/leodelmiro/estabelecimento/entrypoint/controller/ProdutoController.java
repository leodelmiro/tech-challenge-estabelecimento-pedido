package com.leodelmiro.estabelecimento.entrypoint.controller;

import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.core.usecase.produto.*;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.request.CadastraProdutoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ProdutoResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoController {
    public static ProdutoResponse cadastrar(CadastraProdutoRequest cadastraProdutoRequest,
                                            CadastraProdutoUseCase cadastraProdutoUseCase,
                                            ProdutoMapper produtoMapper) {
        // TODO DO VALIDAR OBJETO PRODUTO
        var produto = produtoMapper.toProduto(cadastraProdutoRequest);
        var produtoCadastrado = cadastraProdutoUseCase.cadastrar(produto);
        return produtoMapper.toProdutoResponse(produtoCadastrado);
    }

    public static ProdutoResponse buscar(final Long id,
                                         BuscaProdutoUseCase buscaProdutoUseCase,
                                         ProdutoMapper produtoMapper) {
        var produto = buscaProdutoUseCase.buscar(id);
        return produtoMapper.toProdutoResponse(produto);

    }

    public static Set<ProdutoResponse> listarTodos(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarTodos();
        return transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarLanches(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorLanches();
        return transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarAcompanhamentos(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorAcompanhamentos();
        return transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarBebidas(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorBebidas();
        return transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarSobremesas(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorSobremesas();
        return transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static ProdutoResponse editar(final Long id,
                                         CadastraProdutoRequest cadastraProdutoRequest,
                                         EditaProdutoUseCase editaProdutoUseCase,
                                         ProdutoMapper produtoMapper) {
        var produto = produtoMapper.toProduto(cadastraProdutoRequest);
        produto = editaProdutoUseCase.editar(produto, id);
        return produtoMapper.toProdutoResponse(produto);
    }

    public static void remover(final Long id, RemoveProdutoUseCase removeProdutoUseCase) {
        removeProdutoUseCase.remover(id);
    }


    // TODO USAR PRESENTER
    private static Set<ProdutoResponse> transformarSetProdutosParaProdutoResponse(Set<Produto> produtos,
                                                                                  ProdutoMapper produtoMapper) {
        return produtos.stream()
                .map(produtoMapper::toProdutoResponse)
                .collect(Collectors.toSet()
                );
    }
}
