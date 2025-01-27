package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.dataprovider.gateway.pedido.AdicionaProdutoAoPedidoGatewayImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.AdicionaProdutoAoPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdicionaProdutoAoPedidoConfig {

    @Bean
    public AdicionaProdutoAoPedidoUseCaseImpl adicionaProdutoAoPedidoUseCase(
            AdicionaProdutoAoPedidoGatewayImpl adicionaProdutoAoPedidoGatewayImpl,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl
    ) {
        return new AdicionaProdutoAoPedidoUseCaseImpl(adicionaProdutoAoPedidoGatewayImpl, buscaPedidoUseCaseImpl);
    }
}
