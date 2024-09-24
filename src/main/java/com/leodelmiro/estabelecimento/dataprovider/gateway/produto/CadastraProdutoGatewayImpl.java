package com.leodelmiro.estabelecimento.dataprovider.gateway.produto;

import com.leodelmiro.estabelecimento.dataprovider.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.CadastraProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraProdutoGatewayImpl implements CadastraProdutoGateway {

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
