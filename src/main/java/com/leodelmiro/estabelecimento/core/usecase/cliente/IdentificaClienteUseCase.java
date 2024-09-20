package com.leodelmiro.estabelecimento.core.usecase.cliente;

import com.leodelmiro.estabelecimento.core.domain.CPF;
import com.leodelmiro.estabelecimento.core.domain.Cliente;

import java.util.Optional;

public interface IdentificaClienteUseCase {
    Optional<Cliente> identificar(CPF cpf);
}
