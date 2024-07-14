package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.FechaPedidoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.FechaPedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FechaPedidoConfig {

    @Bean
    public FechaPedidoUseCase fechaPedidoUseCase(
            FechaPedidoAdapter fechaPedidoAdapter,
            BuscaPedidoUseCase buscaPedidoUseCase
    ) {
        return new FechaPedidoUseCase(fechaPedidoAdapter, buscaPedidoUseCase);
    }
}
