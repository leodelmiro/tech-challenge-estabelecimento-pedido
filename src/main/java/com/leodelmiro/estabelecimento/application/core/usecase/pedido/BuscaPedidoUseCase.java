package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.BuscaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.ListaPedidosInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.BuscaPedidoOutputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.ListaPedidosOutputPort;

import java.util.Set;

public class BuscaPedidoUseCase implements BuscaPedidoInputPort {

    private final BuscaPedidoOutputPort buscaPedidoOutputPort;

    public BuscaPedidoUseCase(BuscaPedidoOutputPort buscaPedidoOutputPort) {
        this.buscaPedidoOutputPort = buscaPedidoOutputPort;
    }

    @Override
    public Pedido buscar(Long id) {
        return buscaPedidoOutputPort.buscar(id);
    }

}