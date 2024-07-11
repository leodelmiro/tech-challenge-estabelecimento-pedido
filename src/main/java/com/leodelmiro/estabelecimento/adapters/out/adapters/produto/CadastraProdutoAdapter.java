package com.leodelmiro.estabelecimento.adapters.out.adapters.produto;

import com.leodelmiro.estabelecimento.adapters.out.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.out.produto.CadastraProdutoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraProdutoAdapter implements CadastraProdutoOutputPort {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Produto cadastrar(Produto produto) {
        var produtoEntity = produtoEntityMapper.toProdutoEntity(produto);
        produtoEntity.getImagens().forEach(imagemEntity -> imagemEntity.setProduto(produtoEntity));
        produtoRepository.save(produtoEntity);
        return produtoEntityMapper.toProduto(produtoEntity);
    }
}
