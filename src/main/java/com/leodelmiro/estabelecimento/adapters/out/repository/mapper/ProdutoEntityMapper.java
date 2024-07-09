package com.leodelmiro.estabelecimento.adapters.out.repository.mapper;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ProdutoEntity;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoEntityMapper {
    ProdutoEntity toProdutoEntity(Produto produto);
    Produto toProduto(ProdutoEntity produtoEntity);
}
