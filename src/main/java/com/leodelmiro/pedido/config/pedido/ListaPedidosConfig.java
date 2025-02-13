package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.dataprovider.gateway.pedido.ListaPedidosGatewayImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.ListaPedidosUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaPedidosConfig {

    @Bean
    public ListaPedidosUseCaseImpl listaPedidosUseCase(
            ListaPedidosGatewayImpl listaPedidosGatewayImpl
    ) {
        return new ListaPedidosUseCaseImpl(listaPedidosGatewayImpl);
    }
}
