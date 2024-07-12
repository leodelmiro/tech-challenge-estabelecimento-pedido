package com.leodelmiro.estabelecimento.adapters.in.controller.mapper;

import com.leodelmiro.estabelecimento.adapters.in.controller.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ClienteResponse;
import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    Cliente toCLiente(IdentificaClienteRequest identificaClienteRequest);

    ClienteResponse toClienteResponse(Cliente cliente);
}
