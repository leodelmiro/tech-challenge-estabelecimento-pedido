package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.BuscaPedidoGateway;
import com.leodelmiro.pedido.dataprovider.repository.mapper.PedidoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaPedidoGatewayImpl implements BuscaPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido buscar(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return pedidoEntityMapper.toPedido(pedido);
    }
}
