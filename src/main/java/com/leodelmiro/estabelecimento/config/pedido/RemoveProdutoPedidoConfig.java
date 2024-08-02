package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.RemoveProdutoPedidoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.RemoveProdutoPedidoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.BuscaProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveProdutoPedidoConfig {

    @Bean
    public RemoveProdutoPedidoUseCase removeProdutoPedidoUseCase(
            RemoveProdutoPedidoAdapter removeProdutoPedidoAdapter,
            BuscaPedidoUseCase buscaPedidoUseCase,
            BuscaProdutoUseCase buscaProdutoUseCase
    ) {
        return new RemoveProdutoPedidoUseCase(removeProdutoPedidoAdapter, buscaPedidoUseCase, buscaProdutoUseCase);
    }
}
