package com.leodelmiro.estabelecimento.application.core.usecase.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.cliente.IdentificaClienteOutputPort;

import java.util.Optional;

public class IdentificaClienteUseCase implements IdentificaClienteInputPort {

    private final IdentificaClienteOutputPort identificaClienteOutputPort;

    public IdentificaClienteUseCase(IdentificaClienteOutputPort identificaClienteOutputPort) {
        this.identificaClienteOutputPort = identificaClienteOutputPort;
    }

    @Override
    public Optional<Cliente> identificar(String cpf) {
        return identificaClienteOutputPort.identificar(cpf);
    }
}