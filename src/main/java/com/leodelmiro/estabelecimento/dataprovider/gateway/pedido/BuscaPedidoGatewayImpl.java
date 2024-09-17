package com.leodelmiro.estabelecimento.dataprovider.gateway.pedido;

import com.leodelmiro.estabelecimento.dataprovider.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.BuscaPedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaPedidoGatewayImpl implements BuscaPedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido buscar(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return pedidoEntityMapper.toPedido(pedido);
    }
}
