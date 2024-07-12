package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ClienteMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ClienteResponse;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private IdentificaClienteInputPort identificaClienteInputPort;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteResponse> identifica(@Valid @RequestBody IdentificaClienteRequest identificaClienteRequest) {
        try {
            var cliente = clienteMapper.toCLiente(identificaClienteRequest);
            var clienteIdentificado = identificaClienteInputPort.identificar(cliente);
            var clienteResponse = clienteMapper.toClienteResponse(clienteIdentificado);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(clienteResponse.id()).toUri();
            return ResponseEntity.created(uri).body(clienteResponse);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
