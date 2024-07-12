package com.leodelmiro.estabelecimento.application.ports.in.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;

import java.util.Optional;

public interface IdentificaClienteInputPort {
    Optional<Cliente> identificar(String cpf);
}
