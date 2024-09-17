package com.leodelmiro.estabelecimento.config.cliente;

import com.leodelmiro.estabelecimento.dataprovider.gateway.cliente.IdentificaClienteGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.cliente.impl.IdentificaClienteUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentificaClienteConfig {

    @Bean
    public IdentificaClienteUseCaseImpl identificaClienteUseCase(
            IdentificaClienteGatewayImpl identificaClienteGatewayImpl
    ) {
        return new IdentificaClienteUseCaseImpl(identificaClienteGatewayImpl);
    }
}
