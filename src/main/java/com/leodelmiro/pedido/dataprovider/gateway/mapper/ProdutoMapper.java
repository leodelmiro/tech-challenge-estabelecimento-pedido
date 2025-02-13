package com.leodelmiro.pedido.dataprovider.gateway.mapper;

import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.dataprovider.client.response.ProdutoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoResponse toProduto(Produto produto);

    Produto toProduto(ProdutoResponse produtoResponse);
}
