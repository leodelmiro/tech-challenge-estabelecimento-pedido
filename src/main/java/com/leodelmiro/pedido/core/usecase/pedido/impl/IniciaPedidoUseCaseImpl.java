package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.IniciaPedidoGateway;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.pedido.core.usecase.pedido.IniciaPedidoUseCase;

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
        if (cpf == null || isValidCliente(cpf)) {
            var pedido = new Pedido(
                    cpf,
                    StatusPedido.PENDENTE_FECHAMENTO,
                    new BigDecimal(0),
                    0L
            );
            return iniciaPedidoGateway.iniciar(pedido);
        }

        throw new IllegalStateException("CPF precisa ser null ou Cliente existente");
    }

    private Boolean isValidCliente(String cpf) {
        return identificaClienteUseCase.identificar(cpf);
    }
}