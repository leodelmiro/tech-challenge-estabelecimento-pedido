package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.dataprovider.pedido.FecharPedidoGateway;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.ItemPedidoEntity;
import com.leodelmiro.pedido.dataprovider.repository.mapper.ItemPedidoEntityMapper;
import com.leodelmiro.pedido.dataprovider.repository.mapper.PedidoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FecharPedidoGatewayImpl implements FecharPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Autowired
    private ItemPedidoEntityMapper itemPedidoEntityMapper;

    @Override
    public Pedido fechar(Pedido pedido) {
        var pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);
        List<ItemPedidoEntity> itemPedidoEntities = pedido.getItens().stream()
                .map(item -> {
                    var itemPedidoEntity = itemPedidoEntityMapper.toItemPedidoEntity(item);
                    itemPedidoEntity.setPedido(pedidoEntity);
                    return itemPedidoEntity;
                })
                .toList();

        pedidoEntity.getItens().clear();
        pedidoEntity.addItens(itemPedidoEntities);
        var pedidoAvancado = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoAvancado);
    }
}
