package com.leodelmiro.estabelecimento.adapters.out;

import com.leodelmiro.estabelecimento.adapters.out.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ProdutoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.out.BuscaProdutosOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BuscaProdutosAdapter implements BuscaProdutosOutputPort {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Set<Produto> buscarTodos() {
        List<ProdutoEntity> produtosEntity = produtoRepository.findAll();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> buscarPorLanches() {
        List<ProdutoEntity> produtosEntity = produtoRepository.buscarPorLanches();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> buscarPorAcompanhamentos() {
        List<ProdutoEntity> produtosEntity = produtoRepository.buscarPorAcompanhamentos();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> buscarPorBebidas() {
        List<ProdutoEntity> produtosEntity = produtoRepository.buscarPorBebidas();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    @Override
    public Set<Produto> buscarPorSobremesas() {
        List<ProdutoEntity> produtosEntity = produtoRepository.buscarPorSobremesas();
        return transformarListaProdutosEntityParaSetProdutos(produtosEntity);
    }

    private Set<Produto> transformarListaProdutosEntityParaSetProdutos(List<ProdutoEntity> produtosEntity) {
        return produtosEntity.stream()
                .map(produtoEntity -> produtoEntityMapper.toProduto(produtoEntity))
                .collect(Collectors.toSet());
    }
}
