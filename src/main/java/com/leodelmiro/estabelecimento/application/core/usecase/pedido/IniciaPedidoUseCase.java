package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.core.domain.StatusPedido;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.IniciaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.IniciaPedidoOutputPort;

import java.math.BigDecimal;

public class IniciaPedidoUseCase implements IniciaPedidoInputPort {

    private final IniciaPedidoOutputPort iniciaPedidoOutputPort;
    private final IdentificaClienteInputPort identificaClienteInputPort;

    public IniciaPedidoUseCase(IniciaPedidoOutputPort iniciaPedidoOutputPort,
                               IdentificaClienteInputPort identificaClienteInputPort) {
        this.iniciaPedidoOutputPort = iniciaPedidoOutputPort;
        this.identificaClienteInputPort = identificaClienteInputPort;
    }

    @Override
    public Pedido iniciar(String cpf) {
        var possivelCliente = identificaClienteInputPort.identificar(cpf).orElseThrow();
        var pedido = new Pedido(
                possivelCliente,
                StatusPedido.PENDENTE_FINALIZACAO,
                new BigDecimal(0),
                0L
        );
        return iniciaPedidoOutputPort.iniciar(pedido);
    }
}