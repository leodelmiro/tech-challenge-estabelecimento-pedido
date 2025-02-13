package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.dataprovider.gateway.pedido.RemoveProdutoPedidoGatewayImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.RemoveProdutoPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveProdutoPedidoConfig {

    @Bean
    public RemoveProdutoPedidoUseCaseImpl removeProdutoPedidoUseCase(
            RemoveProdutoPedidoGatewayImpl removeProdutoPedidoGatewayImpl,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl,
            BuscaProdutoUseCaseImpl buscaProdutoUseCaseImpl
    ) {
        return new RemoveProdutoPedidoUseCaseImpl(removeProdutoPedidoGatewayImpl, buscaPedidoUseCaseImpl, buscaProdutoUseCaseImpl);
    }
}
