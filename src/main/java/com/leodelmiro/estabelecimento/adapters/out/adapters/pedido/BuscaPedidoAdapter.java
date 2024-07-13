package com.leodelmiro.estabelecimento.adapters.out.adapters.pedido;

import com.leodelmiro.estabelecimento.adapters.out.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.BuscaPedidoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaPedidoAdapter implements BuscaPedidoOutputPort {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido buscar(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow();
        return pedidoEntityMapper.toPedido(pedido);
    }
}
