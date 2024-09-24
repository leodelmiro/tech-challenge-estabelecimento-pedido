package com.leodelmiro.estabelecimento.dataprovider.gateway.cliente;

import com.leodelmiro.estabelecimento.core.domain.CPF;
import com.leodelmiro.estabelecimento.dataprovider.repository.ClienteRepository;
import com.leodelmiro.estabelecimento.dataprovider.repository.entity.CPFEntity;
import com.leodelmiro.estabelecimento.dataprovider.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.estabelecimento.core.domain.Cliente;
import com.leodelmiro.estabelecimento.core.dataprovider.cliente.IdentificaClienteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IdentificaClienteGatewayImpl implements IdentificaClienteGateway {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public Optional<Cliente> identificar(CPF cpf) {
        return clienteRepository.findClienteByCpf(new CPFEntity(cpf.getCpf()))
                .map(clienteEntityMapper::toCliente);
    }
}
