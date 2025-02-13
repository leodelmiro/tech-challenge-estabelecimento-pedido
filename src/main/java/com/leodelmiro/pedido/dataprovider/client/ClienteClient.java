package com.leodelmiro.pedido.dataprovider.client;

import com.leodelmiro.pedido.dataprovider.client.response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clienteClient", url = "${external-apis.cliente.url}")
public interface ClienteClient {
    @GetMapping("/clientes/{clientId}")
    ClienteResponse buscaCliente(@PathVariable String clientId);
}
