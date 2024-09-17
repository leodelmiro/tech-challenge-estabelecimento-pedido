package com.leodelmiro.estabelecimento.core.usecase.cliente.impl;

import com.leodelmiro.estabelecimento.core.domain.Cliente;
import com.leodelmiro.estabelecimento.core.dataprovider.cliente.CadastraClienteGateway;
import com.leodelmiro.estabelecimento.core.usecase.cliente.CadastraClienteUseCase;
import com.leodelmiro.estabelecimento.core.usecase.cliente.IdentificaClienteUseCase;

public class CadastraClienteUseCaseImpl implements CadastraClienteUseCase {

    private final CadastraClienteGateway cadastraClienteGateway;
    private final IdentificaClienteUseCase identificaClienteUseCase;

    public CadastraClienteUseCaseImpl(CadastraClienteGateway cadastraClienteGateway,
                                      IdentificaClienteUseCase identificaClienteUseCase) {
        this.cadastraClienteGateway = cadastraClienteGateway;
        this.identificaClienteUseCase = identificaClienteUseCase;
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        var possivelCliente = identificaClienteUseCase.identificar(cliente.getCpf());
        return possivelCliente.orElseGet(() -> cadastraClienteGateway.cadastrar(cliente));
    }
}