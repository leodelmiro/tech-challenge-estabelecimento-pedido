package com.leodelmiro.estabelecimento.application.ports.in.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;

public interface CadastraClienteInputPort {
    Cliente cadastrar(Cliente cliente);
}
