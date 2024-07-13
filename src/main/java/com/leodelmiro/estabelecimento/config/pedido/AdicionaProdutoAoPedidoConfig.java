package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.AdicionaProdutoAoPedidoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.AdicionaProdutoAoPedidoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.BuscaProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdicionaProdutoAoPedidoConfig {

    @Bean
    public AdicionaProdutoAoPedidoUseCase adicionaProdutoAoPedidoUseCase(
            AdicionaProdutoAoPedidoAdapter adicionaProdutoAoPedidoAdapter,
            BuscaPedidoUseCase buscaPedidoUseCase,
            BuscaProdutoUseCase buscaProdutoUseCase
    ) {
        return new AdicionaProdutoAoPedidoUseCase(adicionaProdutoAoPedidoAdapter, buscaPedidoUseCase, buscaProdutoUseCase);
    }
}
