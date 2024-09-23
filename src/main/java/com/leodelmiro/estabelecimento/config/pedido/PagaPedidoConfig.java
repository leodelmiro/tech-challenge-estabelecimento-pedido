package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.AdicionaProdutoAoPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.PagaPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.AdicionaProdutoAoPedidoGatewayImpl;
import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.PagaPedidoGatewayImpl;
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
