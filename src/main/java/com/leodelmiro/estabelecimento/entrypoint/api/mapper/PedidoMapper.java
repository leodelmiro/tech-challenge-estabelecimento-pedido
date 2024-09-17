package com.leodelmiro.estabelecimento.entrypoint.api.mapper;

import com.leodelmiro.estabelecimento.entrypoint.api.response.PedidoResponse;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoResponse toPedidoResponse(Pedido pedido);
}
