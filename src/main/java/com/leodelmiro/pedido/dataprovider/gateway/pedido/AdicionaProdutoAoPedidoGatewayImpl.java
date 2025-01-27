package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.AdicionaProdutoAoPedidoGateway;
import com.leodelmiro.pedido.dataprovider.repository.mapper.PedidoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AdicionaProdutoAoPedidoGatewayImpl implements AdicionaProdutoAoPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido adicionar(Pedido pedido) {
        var pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);
        PedidoEntity finalPedidoEntity = pedidoEntity;
        pedidoEntity.getItens().forEach(item -> item.setPedido(finalPedidoEntity));
        pedidoEntity = pedidoRepository.save(finalPedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoEntity);
    }
}
