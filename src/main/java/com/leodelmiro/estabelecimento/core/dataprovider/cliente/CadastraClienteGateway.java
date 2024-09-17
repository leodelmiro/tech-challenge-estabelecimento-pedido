package com.leodelmiro.estabelecimento.core.dataprovider.cliente;

import com.leodelmiro.estabelecimento.core.domain.Cliente;

public interface CadastraClienteGateway {
    Cliente cadastrar(Cliente cliente);
}
