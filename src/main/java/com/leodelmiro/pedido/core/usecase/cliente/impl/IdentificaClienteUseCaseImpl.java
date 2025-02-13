package com.leodelmiro.pedido.core.usecase.cliente.impl;

import com.leodelmiro.pedido.core.dataprovider.cliente.IdentificaClienteGateway;
import com.leodelmiro.pedido.core.usecase.cliente.IdentificaClienteUseCase;

public class IdentificaClienteUseCaseImpl implements IdentificaClienteUseCase {

    private final IdentificaClienteGateway identificaClienteGateway;

    public IdentificaClienteUseCaseImpl(IdentificaClienteGateway identificaClienteGateway) {
        this.identificaClienteGateway = identificaClienteGateway;
    }

    @Override
    public Boolean identificar(String cpf) {
        return identificaClienteGateway.identificar(cpf);
    }
}