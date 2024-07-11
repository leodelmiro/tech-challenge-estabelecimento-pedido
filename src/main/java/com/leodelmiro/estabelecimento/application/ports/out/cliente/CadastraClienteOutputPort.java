package com.leodelmiro.estabelecimento.application.ports.out.cliente;

import com.leodelmiro.estabelecimento.application.core.domain.Cliente;

public interface CadastraClienteOutputPort {
    Cliente cadastrar(Cliente cliente);
}
