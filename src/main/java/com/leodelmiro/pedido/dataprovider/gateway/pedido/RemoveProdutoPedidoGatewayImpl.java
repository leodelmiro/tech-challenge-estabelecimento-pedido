package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.ItemPedidoEntity;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.RemoveProdutoPedidoGateway;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveProdutoPedidoGatewayImpl implements RemoveProdutoPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido remover(Pedido pedido) {
        var pedidoEntity = new PedidoEntity(pedido);
        List<ItemPedidoEntity> itemPedidoEntities = pedido.getItens().stream()
                .map(item -> {
                    var itemPedidoEntity = new ItemPedidoEntity(item, pedido);
                    itemPedidoEntity.setPedido(pedidoEntity);
                    return itemPedidoEntity;
                })
                .toList();

        pedidoEntity.getItens().clear();
        pedidoEntity.addItens(itemPedidoEntities);
        var pedidoAtualizado = pedidoRepository.save(pedidoEntity);
        return pedidoAtualizado.toPedido();
    }
}
