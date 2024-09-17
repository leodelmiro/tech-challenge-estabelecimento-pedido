package com.leodelmiro.estabelecimento.dataprovider.gateway.pedido;

import com.leodelmiro.estabelecimento.dataprovider.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.AdicionaProdutoAoPedidoGateway;
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
