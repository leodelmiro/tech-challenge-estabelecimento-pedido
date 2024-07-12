package com.leodelmiro.estabelecimento.adapters.out.adapters.cliente;

import com.leodelmiro.estabelecimento.adapters.out.repository.ClienteRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.ports.out.cliente.IdentificaClienteOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IdentificaClienteAdapter implements IdentificaClienteOutputPort {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public Optional<Cliente> identificar(String cpf) {
        var possivelClienteEntity = clienteRepository.findClienteByCpf(cpf);
        Optional<Cliente> cliente = Optional.empty();
        if (possivelClienteEntity.isPresent())
            cliente = possivelClienteEntity.map(valor -> clienteEntityMapper.toCLiente(valor));
        return cliente;
    }
}
