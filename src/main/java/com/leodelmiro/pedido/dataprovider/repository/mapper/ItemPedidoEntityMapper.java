package com.leodelmiro.pedido.dataprovider.repository.mapper;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.dataprovider.repository.entity.ItemPedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoEntityMapper {
    ItemPedidoEntity toItemPedidoEntity(ItemPedido itemPedido);
}
