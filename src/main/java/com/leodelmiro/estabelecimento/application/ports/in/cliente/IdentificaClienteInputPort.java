package com.leodelmiro.estabelecimento.application.ports.in.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;

public interface IdentificaClienteInputPort {
    Cliente identificar(Cliente cliente);
}
