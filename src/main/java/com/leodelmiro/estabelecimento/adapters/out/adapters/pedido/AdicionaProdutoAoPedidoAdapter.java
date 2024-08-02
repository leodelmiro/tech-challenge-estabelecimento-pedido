package com.leodelmiro.estabelecimento.adapters.out.adapters.pedido;

import com.leodelmiro.estabelecimento.adapters.out.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ItemPedidoEntityMapper;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.AdicionaProdutoAoPedidoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
        PedidoEntity finalPedidoEntity = pedidoEntity;
        pedidoEntity.getItens().forEach(item -> item.setPedido(finalPedidoEntity));
        pedidoEntity = pedidoRepository.save(finalPedidoEntity);
        return pedidoEntityMapper.toPedido(pedidoEntity);
    }
}
