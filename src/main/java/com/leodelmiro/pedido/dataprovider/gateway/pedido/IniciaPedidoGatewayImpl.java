package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.IniciaPedidoGateway;
import com.leodelmiro.pedido.dataprovider.repository.mapper.PedidoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IniciaPedidoGatewayImpl implements IniciaPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido iniciar(Pedido pedido) {
        var pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);
        var pedidoIniciado = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoIniciado);
    }
}
