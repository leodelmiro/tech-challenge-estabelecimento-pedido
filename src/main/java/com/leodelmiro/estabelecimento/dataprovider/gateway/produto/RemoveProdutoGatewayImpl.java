package com.leodelmiro.estabelecimento.dataprovider.gateway.produto;

import com.leodelmiro.estabelecimento.dataprovider.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.core.dataprovider.produto.RemoveProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

@Component
public class RemoveProdutoGatewayImpl implements RemoveProdutoGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "produto", key = "#id"),
            @CacheEvict(cacheNames = "produtos", allEntries = true),
            @CacheEvict(cacheNames = "lanches", allEntries = true),
            @CacheEvict(cacheNames = "acompanhamentos", allEntries = true),
            @CacheEvict(cacheNames = "bebidas", allEntries = true),
            @CacheEvict(cacheNames = "sobremesas", allEntries = true)
    })
    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}
