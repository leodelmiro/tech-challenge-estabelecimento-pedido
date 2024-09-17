package com.leodelmiro.estabelecimento.dataprovider.repository.mapper;

import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ClienteEntity;
import com.leodelmiro.estabelecimento.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);

    Cliente toCliente(ClienteEntity clienteEntity);
}
