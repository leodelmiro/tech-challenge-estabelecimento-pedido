package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.PagaPedidoUseCaseImpl;
import com.leodelmiro.pedido.dataprovider.gateway.pedido.PagaPedidoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagaPedidoConfig {

    @Bean
    public PagaPedidoUseCaseImpl pagaPedidoUseCase(
            PagaPedidoGatewayImpl pagaPedidoGateway,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl
    ) {
        return new PagaPedidoUseCaseImpl(pagaPedidoGateway, buscaPedidoUseCaseImpl);
    }
}
