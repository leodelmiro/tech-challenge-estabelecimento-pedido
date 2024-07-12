package com.leodelmiro.estabelecimento.adapters.out.adapters.cliente;

import com.leodelmiro.estabelecimento.adapters.out.repository.ClienteRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.ports.out.cliente.CadastraClienteOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraClienteAdapter implements CadastraClienteOutputPort {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public Cliente cadastrar(Cliente cliente) {
        var clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
        clienteRepository.save(clienteEntity);
        return clienteEntityMapper.toCliente(clienteEntity);
    }
}
