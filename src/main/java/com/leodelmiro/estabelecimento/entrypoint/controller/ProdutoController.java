package com.leodelmiro.estabelecimento.entrypoint.controller;

import com.leodelmiro.estabelecimento.core.usecase.produto.*;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.request.CadastraProdutoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.request.EditaProdutoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.entrypoint.presenter.ProdutoPresenter;

import java.util.Set;

public class ProdutoController {
    public static ProdutoResponse cadastrar(CadastraProdutoRequest cadastraProdutoRequest,
                                            CadastraProdutoUseCase cadastraProdutoUseCase,
                                            ProdutoMapper produtoMapper) {
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
        return ProdutoPresenter.transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarLanches(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorLanches();
        return ProdutoPresenter.transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarAcompanhamentos(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorAcompanhamentos();
        return ProdutoPresenter.transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarBebidas(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorBebidas();
        return ProdutoPresenter.transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static Set<ProdutoResponse> listarSobremesas(ListaProdutosUseCase listaProdutosUseCase, ProdutoMapper produtoMapper) {
        var produtos = listaProdutosUseCase.listarPorSobremesas();
        return ProdutoPresenter.transformarSetProdutosParaProdutoResponse(produtos, produtoMapper);
    }


    public static ProdutoResponse editar(final Long id,
                                         EditaProdutoRequest editaProdutoRequest,
                                         EditaProdutoUseCase editaProdutoUseCase,
                                         ProdutoMapper produtoMapper) {
        var produto = produtoMapper.toProduto(editaProdutoRequest);
        produto = editaProdutoUseCase.editar(produto, id);
        return produtoMapper.toProdutoResponse(produto);
    }

    public static void remover(final Long id, RemoveProdutoUseCase removeProdutoUseCase) {
        removeProdutoUseCase.remover(id);
    }

}
