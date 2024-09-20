package com.leodelmiro.estabelecimento.core.usecase.cliente.impl;

import com.leodelmiro.estabelecimento.core.domain.CPF;
import com.leodelmiro.estabelecimento.core.domain.Cliente;
import com.leodelmiro.estabelecimento.core.dataprovider.cliente.IdentificaClienteGateway;
import com.leodelmiro.estabelecimento.core.usecase.cliente.IdentificaClienteUseCase;

import java.util.Optional;

public class IdentificaClienteUseCaseImpl implements IdentificaClienteUseCase {

    private final IdentificaClienteGateway identificaClienteGateway;

    public IdentificaClienteUseCaseImpl(IdentificaClienteGateway identificaClienteGateway) {
        this.identificaClienteGateway = identificaClienteGateway;
    }

    @Override
    public Optional<Cliente> identificar(CPF cpf) {
        return identificaClienteGateway.identificar(cpf);
    }
}