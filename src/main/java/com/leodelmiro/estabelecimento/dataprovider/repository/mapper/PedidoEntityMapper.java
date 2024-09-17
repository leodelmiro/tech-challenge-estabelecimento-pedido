package com.leodelmiro.estabelecimento.dataprovider.repository.mapper;

import com.leodelmiro.estabelecimento.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoEntityMapper {
    PedidoEntity toPedidoEntity(Pedido pedido);

    Pedido toPedido(PedidoEntity pedidoEntity);
}
