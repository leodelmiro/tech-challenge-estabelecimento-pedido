package com.leodelmiro.estabelecimento.entrypoint.controller;

import com.leodelmiro.estabelecimento.core.domain.CPF;
import com.leodelmiro.estabelecimento.core.usecase.cliente.CadastraClienteUseCase;
import com.leodelmiro.estabelecimento.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.ClienteMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ClienteResponse;

public class ClienteController {

    public static ClienteResponse cadastra(IdentificaClienteRequest identificaClienteRequest, CadastraClienteUseCase cadastraClienteUseCase, ClienteMapper clienteMapper) {
        var cliente = clienteMapper.toCLiente(identificaClienteRequest);
        cliente = cadastraClienteUseCase.cadastrar(cliente);
        return clienteMapper.toClienteResponse(cliente);
    }

    public static ClienteResponse identificar(final String cpf, IdentificaClienteUseCase identificaClienteUseCase, ClienteMapper clienteMapper) {
        return identificaClienteUseCase.identificar(new CPF(cpf)).map(clienteMapper::toClienteResponse).orElseThrow();
    }
}
