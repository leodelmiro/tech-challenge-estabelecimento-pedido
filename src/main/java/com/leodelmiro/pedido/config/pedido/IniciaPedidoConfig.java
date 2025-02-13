package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.dataprovider.gateway.pedido.IniciaPedidoGatewayImpl;
import com.leodelmiro.pedido.core.usecase.cliente.impl.IdentificaClienteUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.IniciaPedidoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IniciaPedidoConfig {

    @Bean
    public IniciaPedidoUseCaseImpl iniciaPedidoUseCase(
            IniciaPedidoGatewayImpl iniciaPedidoGatewayImpl,
            IdentificaClienteUseCaseImpl identificaClienteUseCaseImpl
    ) {
        return new IniciaPedidoUseCaseImpl(iniciaPedidoGatewayImpl, identificaClienteUseCaseImpl);
    }
}
