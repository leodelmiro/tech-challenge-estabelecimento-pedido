package com.leodelmiro.estabelecimento.entrypoint.presenter;

import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.PedidoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.response.PedidoResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class PedidoPresenter {
    public static Set<PedidoResponse> transformarSetPedidosParaPedidosResponse(Set<Pedido> pedidos, PedidoMapper pedidoMapper) {
        return pedidos.stream()
                .map(pedidoMapper::toPedidoResponse)
                .collect(Collectors.toSet()
                );
    }
}
