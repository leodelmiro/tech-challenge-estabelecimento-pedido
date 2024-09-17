package com.leodelmiro.estabelecimento.entrypoint.api.mapper;

import com.leodelmiro.estabelecimento.entrypoint.api.request.IdentificaClienteRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ClienteResponse;
import com.leodelmiro.estabelecimento.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    Cliente toCLiente(IdentificaClienteRequest identificaClienteRequest);

    ClienteResponse toClienteResponse(Cliente cliente);
}
