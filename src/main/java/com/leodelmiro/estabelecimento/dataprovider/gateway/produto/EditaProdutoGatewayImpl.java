package com.leodelmiro.estabelecimento.dataprovider.gateway.produto;

import com.leodelmiro.estabelecimento.dataprovider.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.EditaProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditaProdutoGatewayImpl implements EditaProdutoGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Produto editar(Produto produto, Long id) {
        var produtoASerEditado = produtoEntityMapper.toProdutoEntity(produto);
        produtoASerEditado.getImagens().forEach(imagemEntity -> imagemEntity.setProduto(produtoASerEditado));
        produtoASerEditado.setId(id);
        var produtoEditado = produtoRepository.save(produtoASerEditado);
        return produtoEntityMapper.toProduto(produtoEditado);
    }

}
