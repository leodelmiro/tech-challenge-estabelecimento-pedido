package com.leodelmiro.estabelecimento.dataprovider.gateway.cliente;

import com.leodelmiro.estabelecimento.dataprovider.repository.ClienteRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Cliente;
import com.leodelmiro.estabelecimento.core.dataprovider.cliente.CadastraClienteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraClienteGatewayImpl implements CadastraClienteGateway {

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
