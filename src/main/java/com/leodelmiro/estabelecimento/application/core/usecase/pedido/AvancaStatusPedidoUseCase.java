package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.AvancaStatusPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.BuscaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.AvancaStatusPedidoOutputPort;

import static com.leodelmiro.estabelecimento.application.core.domain.StatusPedido.FINALIZADO;

public class AvancaStatusPedidoUseCase implements AvancaStatusPedidoInputPort {

    private final AvancaStatusPedidoOutputPort avancaStatusPedidoOutputPort;
    private final BuscaPedidoInputPort buscaPedidoInputPort;

    public AvancaStatusPedidoUseCase(AvancaStatusPedidoOutputPort avancaStatusPedidoOutputPort,
                                     BuscaPedidoInputPort buscaPedidoInputPort) {
        this.avancaStatusPedidoOutputPort = avancaStatusPedidoOutputPort;
        this.buscaPedidoInputPort = buscaPedidoInputPort;
    }

    @Override
    public Pedido avancar(Long id) {
        var pedidoAAvancar = buscaPedidoInputPort.buscar(id);
        validarPedido(pedidoAAvancar);
        pedidoAAvancar.setStatus(pedidoAAvancar.getStatus().next());
        return avancaStatusPedidoOutputPort.avancar(pedidoAAvancar);
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