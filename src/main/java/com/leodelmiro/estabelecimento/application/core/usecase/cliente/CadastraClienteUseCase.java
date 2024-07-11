package com.leodelmiro.estabelecimento.application.core.usecase.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.CadastraClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.cliente.CadastraClienteOutputPort;

public class CadastraClienteUseCase implements CadastraClienteInputPort {

    private final CadastraClienteOutputPort cadastraClienteOutputPort;

    public CadastraClienteUseCase(CadastraClienteOutputPort cadastraClienteOutputPort) {
        this.cadastraClienteOutputPort = cadastraClienteOutputPort;
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        return cadastraClienteOutputPort.cadastrar(cliente);
    }
}