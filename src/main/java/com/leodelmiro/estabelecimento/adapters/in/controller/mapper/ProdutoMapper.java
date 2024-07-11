package com.leodelmiro.estabelecimento.adapters.in.controller.mapper;

import com.leodelmiro.estabelecimento.adapters.in.controller.request.CriaProdutoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    Produto toProduto(CriaProdutoRequest criaProdutoRequest);

    ProdutoResponse toProdutoResponse(Produto produto);
}
