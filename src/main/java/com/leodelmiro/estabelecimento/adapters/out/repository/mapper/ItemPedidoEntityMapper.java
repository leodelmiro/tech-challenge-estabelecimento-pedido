package com.leodelmiro.estabelecimento.adapters.out.repository.mapper;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ItemPedidoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoEntityMapper {
    ItemPedidoEntity toItemPedidoEntity(ItemPedido itemPedido);

    ItemPedido toItemPedido(ItemPedidoEntity itemPedidoEntity);
}
