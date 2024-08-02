package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.AvancaStatusPedidoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.AvancaStatusPedidoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.BuscaPedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvancaStatusPedidoConfig {

    @Bean
    public AvancaStatusPedidoUseCase avancaStatusPedidoUseCase(
            AvancaStatusPedidoAdapter avancaStatusPedidoAdapter,
            BuscaPedidoUseCase buscaPedidoUseCase
    ) {
        return new AvancaStatusPedidoUseCase(avancaStatusPedidoAdapter, buscaPedidoUseCase);
    }
}
