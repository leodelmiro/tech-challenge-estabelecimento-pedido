package com.leodelmiro.estabelecimento.adapters.out.adapters.pedido;

import com.leodelmiro.estabelecimento.adapters.out.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ItemPedidoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ItemPedidoEntityMapper;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.AvancaStatusPedidoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvancaStatusPedidoAdapter implements AvancaStatusPedidoOutputPort {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Autowired
    private ItemPedidoEntityMapper itemPedidoEntityMapper;

    @Override
    public Pedido avancar(Pedido pedido) {
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
