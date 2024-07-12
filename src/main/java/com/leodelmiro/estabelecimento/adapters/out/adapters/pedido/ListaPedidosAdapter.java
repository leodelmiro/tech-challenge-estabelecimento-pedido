package com.leodelmiro.estabelecimento.adapters.out.adapters.pedido;

import com.leodelmiro.estabelecimento.adapters.out.repository.PedidoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.PedidoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ProdutoEntity;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.PedidoEntityMapper;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.ListaPedidosOutputPort;
import com.leodelmiro.estabelecimento.application.ports.out.produto.ListaProdutosOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ListaPedidosAdapter implements ListaPedidosOutputPort {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Set<Pedido> listarTodos() {
        List<PedidoEntity> pedidosEntity = pedidoRepository.findAll();
        return transformarListaPedidosEntityParaSetPedidos(pedidosEntity);
    }

    @Override
    public Set<Pedido> listarPedidosNaFila() {
        List<PedidoEntity> pedidosEntity = pedidoRepository.findPedidosPagosEPendentesDeFinalizacao();
        return transformarListaPedidosEntityParaSetPedidos(pedidosEntity);
    }

    private Set<Pedido> transformarListaPedidosEntityParaSetPedidos(List<PedidoEntity> pedidosEntity) {
        return pedidosEntity.stream()
                .map(produtoEntity -> pedidoEntityMapper.toPedido(produtoEntity))
                .collect(Collectors.toSet());
    }
}
