package com.leodelmiro.estabelecimento.core.dataprovider.cliente;

import com.leodelmiro.estabelecimento.core.domain.CPF;
import com.leodelmiro.estabelecimento.core.domain.Cliente;

import java.util.Optional;

public interface IdentificaClienteGateway {
    Optional<Cliente> identificar(CPF cpf);
}
