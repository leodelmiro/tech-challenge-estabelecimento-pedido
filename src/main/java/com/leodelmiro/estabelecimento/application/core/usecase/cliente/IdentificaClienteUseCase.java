package com.leodelmiro.estabelecimento.application.core.usecase.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.CadastraClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.cliente.IdentificaClienteOutputPort;

public class IdentificaClienteUseCase implements IdentificaClienteInputPort {

    private final IdentificaClienteOutputPort identificaClienteOutputPort;
    private final CadastraClienteInputPort cadastraClienteInputPort;

    public IdentificaClienteUseCase(IdentificaClienteOutputPort identificaClienteOutputPort, CadastraClienteInputPort cadastraClienteInputPort) {
        this.identificaClienteOutputPort = identificaClienteOutputPort;
        this.cadastraClienteInputPort = cadastraClienteInputPort;
    }

    @Override
    public Cliente identificar(Cliente cliente) {
        var possivelCliente = identificaClienteOutputPort.identificar(cliente.getCpf());
        return possivelCliente.orElseGet(() -> cadastraClienteInputPort.cadastrar(cliente));
    }
}