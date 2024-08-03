package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ClienteMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ClienteResponse;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.CadastraClienteInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.cliente.IdentificaClienteInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Cliente", description = "Endpoints relacionados ao Cliente")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private IdentificaClienteInputPort identificaClienteInputPort;

    @Autowired
    private CadastraClienteInputPort cadastraClienteInputPort;

    @Autowired
    private ClienteMapper clienteMapper;

    @Operation(
            summary = "Cadastro de Cliente",
            description = "Realiza um cadastro de um novo Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado")
    })
    @PostMapping
    public ResponseEntity<ClienteResponse> cadastra(@Valid @RequestBody IdentificaClienteRequest identificaClienteRequest) {
        var cliente = clienteMapper.toCLiente(identificaClienteRequest);
        cliente = cadastraClienteInputPort.cadastrar(cliente);
        var clienteResponse = clienteMapper.toClienteResponse(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteResponse.id()).toUri();
        return ResponseEntity.created(uri).body(clienteResponse);
    }

    @Operation(
            summary = "Identifica Cliente por CPF",
            description = "Permite identificar um Cliente por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente identificado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
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
