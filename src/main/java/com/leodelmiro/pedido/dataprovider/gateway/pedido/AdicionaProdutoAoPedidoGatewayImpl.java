package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.AdicionaProdutoAoPedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AdicionaProdutoAoPedidoGatewayImpl implements AdicionaProdutoAoPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido adicionar(Pedido pedido) {
        var pedidoEntity = new PedidoEntity(pedido);
        PedidoEntity finalPedidoEntity = pedidoEntity;
        pedidoEntity.getItens().forEach(item -> item.setPedido(finalPedidoEntity));
        pedidoEntity = pedidoRepository.save(finalPedidoEntity);
        return pedidoEntity.toPedido();
    }
}
