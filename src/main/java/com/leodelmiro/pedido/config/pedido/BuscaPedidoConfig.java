package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.dataprovider.gateway.pedido.BuscaPedidoGatewayImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaPedidoConfig {

    @Bean
    public BuscaPedidoUseCaseImpl buscaPedidoUseCase(
            BuscaPedidoGatewayImpl buscaPedidoGatewayImpl
    ) {
        return new BuscaPedidoUseCaseImpl(buscaPedidoGatewayImpl);
    }
}
