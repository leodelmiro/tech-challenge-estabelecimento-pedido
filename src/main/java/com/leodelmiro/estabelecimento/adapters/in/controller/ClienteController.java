package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ClienteMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ClienteResponse;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.CadastraClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private IdentificaClienteInputPort identificaClienteInputPort;

    @Autowired
    private CadastraClienteInputPort cadastraClienteInputPort;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastra(@Valid @RequestBody IdentificaClienteRequest identificaClienteRequest) {
        try {
            var cliente = clienteMapper.toCLiente(identificaClienteRequest);
            cliente = cadastraClienteInputPort.cadastrar(cliente);
            var clienteResponse = clienteMapper.toClienteResponse(cliente);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(clienteResponse.id()).toUri();
            return ResponseEntity.created(uri).body(clienteResponse);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> identificar(@PathVariable final String cpf) {
        try {
            var cliente = identificaClienteInputPort.identificar(cpf).orElseThrow();
            return ResponseEntity.ok().body(clienteMapper.toClienteResponse(cliente));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
