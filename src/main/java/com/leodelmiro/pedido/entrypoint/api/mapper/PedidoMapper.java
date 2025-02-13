package com.leodelmiro.pedido.entrypoint.api.mapper;

import com.leodelmiro.pedido.entrypoint.api.response.PedidoResponse;
import com.leodelmiro.pedido.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoResponse toPedidoResponse(Pedido pedido);
}
