package com.leodelmiro.estabelecimento.adapters.out.adapters.pedido;

import com.leodelmiro.estabelecimento.adapters.out.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.IniciaPedidoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IniciaPedidoAdapter implements IniciaPedidoOutputPort {

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
