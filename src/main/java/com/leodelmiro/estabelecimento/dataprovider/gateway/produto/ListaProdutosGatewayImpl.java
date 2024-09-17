package com.leodelmiro.estabelecimento.dataprovider.gateway.produto;

import com.leodelmiro.estabelecimento.dataprovider.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ProdutoEntity;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.ListaProdutosGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ListaProdutosGatewayImpl implements ListaProdutosGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Set<Produto> listarTodos() {
        List<ProdutoEntity> produtosEntity = produtoRepository.findAll();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> listarPorLanches() {
        List<ProdutoEntity> produtosEntity = produtoRepository.listarPorLanches();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> listarPorAcompanhamentos() {
        List<ProdutoEntity> produtosEntity = produtoRepository.listarPorAcompanhamentos();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> listarPorBebidas() {
        List<ProdutoEntity> produtosEntity = produtoRepository.listarPorBebidas();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> listarPorSobremesas() {
        List<ProdutoEntity> produtosEntity = produtoRepository.listarPorSobremesas();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    private Set<Produto> transformarListaProdutosEntityParaSetProdutos(List<ProdutoEntity> produtosEntity) {
        return produtosEntity.stream()
                .map(produtoEntity -> produtoEntityMapper.toProduto(produtoEntity))
                .collect(Collectors.toSet());
    }
}
