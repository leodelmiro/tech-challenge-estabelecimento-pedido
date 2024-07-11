package com.leodelmiro.estabelecimento.adapters.out.adapters.produto;

import com.leodelmiro.estabelecimento.adapters.out.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ProdutoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.out.produto.BuscaProdutoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaProdutoAdapter implements BuscaProdutoOutputPort {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Produto buscar(Long id) {
        ProdutoEntity produto = produtoRepository.findById(id).orElseThrow();
        return produtoEntityMapper.toProduto(produto);
    }
}
