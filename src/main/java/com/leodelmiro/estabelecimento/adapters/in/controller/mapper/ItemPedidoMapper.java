package com.leodelmiro.estabelecimento.adapters.in.controller.mapper;

import com.leodelmiro.estabelecimento.adapters.in.controller.response.ItemPedidoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedidoResponse toPedidoResponse(ItemPedido itemPedido);
}
