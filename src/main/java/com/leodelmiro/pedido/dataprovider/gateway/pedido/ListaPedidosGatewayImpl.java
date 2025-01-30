package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.ListaPedidosGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ListaPedidosGatewayImpl implements ListaPedidosGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Set<Pedido> listarTodos() {
        List<PedidoEntity> pedidosEntity = pedidoRepository.findAll();
        return transformarListaPedidosEntityParaSetPedidos(pedidosEntity);
    }

    @Override
    public Set<Pedido> listarPedidosNaFila() {
        List<PedidoEntity> pedidosEntity = pedidoRepository.findPedidosPagosEPendentesDeFinalizacao();
        return transformarListaPedidosEntityParaSetPedidos(pedidosEntity);
    }

    private Set<Pedido> transformarListaPedidosEntityParaSetPedidos(List<PedidoEntity> pedidosEntity) {
        return pedidosEntity.stream()
                .map(PedidoEntity::toPedido)
                .collect(Collectors.toSet());
    }
}
