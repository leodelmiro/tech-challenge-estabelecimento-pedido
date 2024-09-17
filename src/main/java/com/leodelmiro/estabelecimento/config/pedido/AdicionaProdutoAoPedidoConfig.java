package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.AdicionaProdutoAoPedidoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.AdicionaProdutoAoPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdicionaProdutoAoPedidoConfig {

    @Bean
    public AdicionaProdutoAoPedidoUseCaseImpl adicionaProdutoAoPedidoUseCase(
            AdicionaProdutoAoPedidoGatewayImpl adicionaProdutoAoPedidoGatewayImpl,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl,
            BuscaProdutoUseCaseImpl buscaProdutoUseCaseImpl
    ) {
        return new AdicionaProdutoAoPedidoUseCaseImpl(adicionaProdutoAoPedidoGatewayImpl, buscaPedidoUseCaseImpl, buscaProdutoUseCaseImpl);
    }
}
