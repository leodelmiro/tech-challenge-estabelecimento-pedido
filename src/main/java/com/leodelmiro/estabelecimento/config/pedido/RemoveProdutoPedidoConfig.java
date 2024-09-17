package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.RemoveProdutoPedidoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.RemoveProdutoPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
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
