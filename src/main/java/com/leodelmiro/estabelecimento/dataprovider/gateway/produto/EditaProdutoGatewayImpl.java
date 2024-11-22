package com.leodelmiro.estabelecimento.dataprovider.gateway.produto;

import com.leodelmiro.estabelecimento.dataprovider.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.EditaProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

@Component
public class EditaProdutoGatewayImpl implements EditaProdutoGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "produto", key = "#id"),
            @CacheEvict(cacheNames = "produtos", allEntries = true),
            @CacheEvict(cacheNames = "lanches", allEntries = true),
            @CacheEvict(cacheNames = "acompanhamentos", allEntries = true),
            @CacheEvict(cacheNames = "bebidas", allEntries = true),
            @CacheEvict(cacheNames = "sobremesas", allEntries = true)
    })
    public Produto editar(Produto produto, Long id) {
        var produtoASerEditado = produtoEntityMapper.toProdutoEntity(produto);
        produtoASerEditado.getImagens().forEach(imagemEntity -> imagemEntity.setProduto(produtoASerEditado));
        produtoASerEditado.setId(id);
        var produtoEditado = produtoRepository.save(produtoASerEditado);
        return produtoEntityMapper.toProduto(produtoEditado);
    }

}
