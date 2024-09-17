package com.leodelmiro.estabelecimento.dataprovider.gateway.produto;

import com.leodelmiro.estabelecimento.dataprovider.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.RemoveProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveProdutoGatewayImpl implements RemoveProdutoGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}
