package com.leodelmiro.estabelecimento.dataprovider.repository.mapper;

import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ProdutoEntity;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoEntityMapper {
    ProdutoEntity toProdutoEntity(Produto produto);
    Produto toProduto(ProdutoEntity produtoEntity);
}
