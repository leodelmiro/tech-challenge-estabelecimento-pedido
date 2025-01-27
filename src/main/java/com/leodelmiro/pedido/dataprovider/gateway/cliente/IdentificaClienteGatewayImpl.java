package com.leodelmiro.pedido.dataprovider.gateway.cliente;

import com.leodelmiro.pedido.core.dataprovider.cliente.IdentificaClienteGateway;
import com.leodelmiro.pedido.dataprovider.client.ClienteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdentificaClienteGatewayImpl implements IdentificaClienteGateway {

    @Autowired
    private ClienteClient clienteClient;

    @Override
    public Boolean identificar(String cpf) {
        return clienteClient.buscaCliente(cpf) != null;
    }
}
