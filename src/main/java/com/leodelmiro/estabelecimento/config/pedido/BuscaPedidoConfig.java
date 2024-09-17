package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.BuscaPedidoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
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
