package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.ListaPedidosGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.ListaPedidosUseCaseImpl;
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
