package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.AvancaStatusPedidoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.AvancaStatusPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
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
