package com.leodelmiro.estabelecimento.dataprovider.repository.mapper;

import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ItemPedidoEntity;
import com.leodelmiro.estabelecimento.core.domain.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoEntityMapper {
    ItemPedidoEntity toItemPedidoEntity(ItemPedido itemPedido);

    ItemPedido toItemPedido(ItemPedidoEntity itemPedidoEntity);
}
