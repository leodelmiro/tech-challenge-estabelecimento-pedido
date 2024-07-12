package com.leodelmiro.estabelecimento.application.core.usecase.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.CadastraClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.cliente.CadastraClienteOutputPort;

public class CadastraClienteUseCase implements CadastraClienteInputPort {

    private final CadastraClienteOutputPort cadastraClienteOutputPort;
    private final IdentificaClienteInputPort identificaClienteInputPort;

    public CadastraClienteUseCase(CadastraClienteOutputPort cadastraClienteOutputPort,
                                  IdentificaClienteInputPort identificaClienteInputPort) {
        this.cadastraClienteOutputPort = cadastraClienteOutputPort;
        this.identificaClienteInputPort = identificaClienteInputPort;
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        var possivelCliente = identificaClienteInputPort.identificar(cliente.getCpf());
        return possivelCliente.orElseGet(() -> cadastraClienteOutputPort.cadastrar(cliente));
    }
}