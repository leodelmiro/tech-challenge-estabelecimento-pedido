package com.leodelmiro.estabelecimento.adapters.out.adapters.pedido;

import com.leodelmiro.estabelecimento.adapters.out.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ItemPedidoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ItemPedidoEntityMapper;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.AdicionaProdutoAoPedidoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdicionaProdutoAoPedidoAdapter implements AdicionaProdutoAoPedidoOutputPort {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Autowired
    private ItemPedidoEntityMapper itemPedidoEntityMapper;


    @Override
    public Pedido adicionar(Pedido pedido) {
        var pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);
        pedidoEntity.addItens(pedido.getItens().stream().map(
                item -> itemPedidoEntityMapper.toItemPedidoEntity(item)).toList()
        );
        for (ItemPedidoEntity item : pedidoEntity.getItens()) {
            item.setPedido(pedidoEntity);
        }
        pedidoEntity = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoEntity);
    }
}
