package com.leodelmiro.estabelecimento.adapters.out.repository.mapper;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoEntityMapper {
    PedidoEntity toPedidoEntity(Pedido pedido);

    Pedido toPedido(PedidoEntity pedidoEntity);
}
