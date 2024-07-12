package com.leodelmiro.estabelecimento.adapters.out.repository.mapper;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ClienteEntity;
import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);

    Cliente toCliente(ClienteEntity clienteEntity);
}
