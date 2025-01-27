package com.leodelmiro.pedido.dataprovider.repository.mapper;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoEntityMapper {
    PedidoEntity toPedidoEntity(Pedido pedido);

    Pedido toPedido(PedidoEntity pedidoEntity);
}
