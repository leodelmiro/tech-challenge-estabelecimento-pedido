package com.leodelmiro.estabelecimento.core.usecase.pedido.impl;

import com.leodelmiro.estabelecimento.core.domain.Cliente;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.domain.StatusPedido;
import com.leodelmiro.estabelecimento.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.IniciaPedidoGateway;
import com.leodelmiro.estabelecimento.core.usecase.pedido.IniciaPedidoUseCase;

import java.math.BigDecimal;

public class IniciaPedidoUseCaseImpl implements IniciaPedidoUseCase {

    private final IniciaPedidoGateway iniciaPedidoGateway;
    private final IdentificaClienteUseCase identificaClienteUseCase;

    public IniciaPedidoUseCaseImpl(IniciaPedidoGateway iniciaPedidoGateway,
                                   IdentificaClienteUseCase identificaClienteUseCase) {
        this.iniciaPedidoGateway = iniciaPedidoGateway;
        this.identificaClienteUseCase = identificaClienteUseCase;
    }

    @Override
    public Pedido iniciar(String cpf) {
        Cliente possivelCliente = retornarPossivelCliente(cpf);
        var pedido = new Pedido(
                possivelCliente,
                StatusPedido.PENDENTE_FECHAMENTO,
                new BigDecimal(0),
                0L
        );
        return iniciaPedidoGateway.iniciar(pedido);
    }

    private Cliente retornarPossivelCliente(String cpf) {
        Cliente possivelCliente = null;
        if(!cpf.isBlank()) possivelCliente = identificaClienteUseCase.identificar(cpf).orElseThrow();
        return possivelCliente;
    }
}