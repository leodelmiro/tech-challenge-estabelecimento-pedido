package com.leodelmiro.estabelecimento.adapters.in.controller.mapper;

import com.leodelmiro.estabelecimento.adapters.in.controller.response.PedidoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoResponse toPedidoResponse(Pedido pedido);
}
