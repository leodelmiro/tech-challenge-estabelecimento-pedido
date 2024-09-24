package com.leodelmiro.estabelecimento.entrypoint.api.mapper;

import com.leodelmiro.estabelecimento.entrypoint.api.request.EditaProdutoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.entrypoint.api.request.CadastraProdutoRequest;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    Produto toProduto(CadastraProdutoRequest cadastraProdutoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    Produto toProduto(EditaProdutoRequest editaProdutoRequest);

    ProdutoResponse toProdutoResponse(Produto produto);
}
