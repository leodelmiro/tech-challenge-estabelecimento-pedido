package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.core.domain.StatusPedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.BuscaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.FechaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.FechaPedidoOutputPort;

public class FechaPedidoUseCase implements FechaPedidoInputPort {

    private final FechaPedidoOutputPort fechaPedidoOutputPort;
    private final BuscaPedidoInputPort buscaPedidoInputPort;

    public FechaPedidoUseCase(FechaPedidoOutputPort fechaPedidoOutputPort,
                              BuscaPedidoInputPort buscaPedidoInputPort) {
        this.fechaPedidoOutputPort = fechaPedidoOutputPort;
        this.buscaPedidoInputPort = buscaPedidoInputPort;
    }

    @Override
    public Pedido fechar(Long id) {
        var pedidoAFechar = buscaPedidoInputPort.buscar(id);
        if (pedidoAFechar.getItens().isEmpty()) throw new IllegalStateException("Impossível fechar pedido sem itens");
        pedidoAFechar.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        // TODO ENVIAR PARA SISTEMA EXTERNO DE PAGAMENTO E GERAR QRCODE
        return fechaPedidoOutputPort.fechar(pedidoAFechar);
    }
}