package com.leodelmiro.estabelecimento.core.usecase.pedido.impl;

import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.AvancaStatusPedidoGateway;
import com.leodelmiro.estabelecimento.core.usecase.pedido.AvancaStatusPedidoUseCase;
import com.leodelmiro.estabelecimento.core.usecase.pedido.BuscaPedidoUseCase;

import static com.leodelmiro.estabelecimento.core.domain.StatusPedido.FINALIZADO;

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
        if (isPedidoSemItens(pedidoAAvancar)) throw new IllegalStateException("Impossível avancar pedido sem itens");
        if (isPedidoFinalizado(pedidoAAvancar)) throw new IllegalStateException("Impossível avancar pedido já finalizado");
    }

    private boolean isPedidoFinalizado(Pedido pedido) {
        return pedido.getStatus() == FINALIZADO;
    }

    private boolean isPedidoSemItens(Pedido pedido) {
        return pedido.getItens().isEmpty();
    }
}