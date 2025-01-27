package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.AvancaStatusPedidoGateway;
import com.leodelmiro.pedido.core.usecase.pedido.AvancaStatusPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;

import static com.leodelmiro.pedido.core.domain.StatusPedido.*;

public class AvancaStatusPedidoUseCaseImpl implements AvancaStatusPedidoUseCase {

    private final AvancaStatusPedidoGateway avancaStatusPedidoGateway;
    private final BuscaPedidoUseCase buscaPedidoUseCase;

    public AvancaStatusPedidoUseCaseImpl(AvancaStatusPedidoGateway avancaStatusPedidoGateway,
                                         BuscaPedidoUseCase buscaPedidoUseCase) {
        this.avancaStatusPedidoGateway = avancaStatusPedidoGateway;
        this.buscaPedidoUseCase = buscaPedidoUseCase;
    }

    @Override
    public Pedido avancar(Long id) {
        var pedidoAAvancar = buscaPedidoUseCase.buscar(id);
        validarPedido(pedidoAAvancar);
        pedidoAAvancar.avancarStatus();
        return avancaStatusPedidoGateway.avancar(pedidoAAvancar);
    }

    private void validarPedido(Pedido pedidoAAvancar) {
        if (isPedidoAFechar(pedidoAAvancar)) throw new IllegalStateException("Pedidos a fechar devem ser feito pelo Fechar Pedido");
        if (isPedidoFinalizado(pedidoAAvancar)) throw new IllegalStateException("Impossível avancar pedido já finalizado");
    }

    private boolean isPedidoFinalizado(Pedido pedido) {
        return pedido.getStatus() == FINALIZADO;
    }

    private boolean isPedidoAFechar(Pedido pedido) {
        return pedido.getStatus() == PENDENTE_FECHAMENTO;
    }
}