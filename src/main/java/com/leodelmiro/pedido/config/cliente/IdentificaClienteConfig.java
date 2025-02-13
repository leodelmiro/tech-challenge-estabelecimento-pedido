package com.leodelmiro.pedido.config.cliente;

import com.leodelmiro.pedido.dataprovider.gateway.cliente.IdentificaClienteGatewayImpl;
import com.leodelmiro.pedido.core.usecase.cliente.impl.IdentificaClienteUseCaseImpl;
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
