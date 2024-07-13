package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.BuscaPedidoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.BuscaPedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaPedidoConfig {

    @Bean
    public BuscaPedidoUseCase buscaPedidoUseCase(
            BuscaPedidoAdapter buscaPedidoAdapter
    ) {
        return new BuscaPedidoUseCase(buscaPedidoAdapter);
    }
}
