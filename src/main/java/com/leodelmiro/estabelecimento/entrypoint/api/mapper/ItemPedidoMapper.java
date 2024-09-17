package com.leodelmiro.estabelecimento.entrypoint.api.mapper;

import com.leodelmiro.estabelecimento.entrypoint.api.response.ItemPedidoResponse;
import com.leodelmiro.estabelecimento.core.domain.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedidoResponse toPedidoResponse(ItemPedido itemPedido);
}
