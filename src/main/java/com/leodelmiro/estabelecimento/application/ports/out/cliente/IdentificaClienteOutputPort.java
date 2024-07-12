package com.leodelmiro.estabelecimento.application.ports.out.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;

import java.util.Optional;

public interface IdentificaClienteOutputPort {
    Optional<Cliente> identificar(String cpf);
}
