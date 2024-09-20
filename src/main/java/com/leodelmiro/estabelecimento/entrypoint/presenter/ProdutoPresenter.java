package com.leodelmiro.estabelecimento.entrypoint.presenter;

import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ProdutoResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoPresenter {
    public static Set<ProdutoResponse> transformarSetProdutosParaProdutoResponse(Set<Produto> produtos,
                                                                                 ProdutoMapper produtoMapper) {
        return produtos.stream()
                .map(produtoMapper::toProdutoResponse)
                .collect(Collectors.toSet()
                );
    }
}
