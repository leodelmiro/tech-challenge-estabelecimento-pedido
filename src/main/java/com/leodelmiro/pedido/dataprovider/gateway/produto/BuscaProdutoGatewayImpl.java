package com.leodelmiro.pedido.dataprovider.gateway.produto;

import com.leodelmiro.pedido.core.dataprovider.produto.BuscaProdutoGateway;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.dataprovider.client.ProdutoClient;
import com.leodelmiro.pedido.dataprovider.gateway.mapper.ProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaProdutoGatewayImpl implements BuscaProdutoGateway {

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Override
    public Produto buscar(Long id) {
        return produtoMapper.toProduto(produtoClient.buscaProduto(id));
    }
}
