package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.dataprovider.gateway.pedido.AvancaStatusPedidoGatewayImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.AvancaStatusPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvancaStatusPedidoConfig {

    @Bean
    public AvancaStatusPedidoUseCaseImpl avancaStatusPedidoUseCase(
            AvancaStatusPedidoGatewayImpl avancaStatusPedidoGatewayImpl,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl
    ) {
        return new AvancaStatusPedidoUseCaseImpl(avancaStatusPedidoGatewayImpl, buscaPedidoUseCaseImpl);
    }
}
