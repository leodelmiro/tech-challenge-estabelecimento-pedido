package com.leodelmiro.estabelecimento.dataprovider.gateway.pedido;

import com.leodelmiro.estabelecimento.core.dataprovider.pedido.PagaPedidoGateway;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.dataprovider.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.PedidoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PagaPedidoGatewayImpl implements PagaPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido pagar(Pedido pedido) {
        var pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);
        pedidoEntity = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoEntity);
    }
}
