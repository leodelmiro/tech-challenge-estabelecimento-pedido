package com.leodelmiro.estabelecimento.entrypoint.api;

import com.leodelmiro.estabelecimento.core.usecase.cliente.CadastraClienteUseCase;
import com.leodelmiro.estabelecimento.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.ClienteMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ClienteResponse;
import com.leodelmiro.estabelecimento.entrypoint.controller.ClienteController;
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
public class ClienteApi {

    @Autowired
    private IdentificaClienteUseCase identificaClienteUseCase;

    @Autowired
    private CadastraClienteUseCase cadastraClienteUseCase;

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
        // TODO REFACTOR PARA PRESENTER
        var clienteResponse = ClienteController.cadastra(identificaClienteRequest, cadastraClienteUseCase, clienteMapper);
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
        // TODO REFACTOR PARA PRESENTER
        try {
            var cliente = ClienteController.identificar(cpf, identificaClienteUseCase, clienteMapper);
            return ResponseEntity.ok().body(cliente);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
