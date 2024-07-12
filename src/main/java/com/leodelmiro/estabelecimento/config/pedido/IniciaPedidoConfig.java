package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.IniciaPedidoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.IniciaPedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IniciaPedidoConfig {

    @Bean
    public IniciaPedidoUseCase iniciaPedidoUseCase(
            IniciaPedidoAdapter iniciaPedidoAdapter,
            IdentificaClienteUseCase identificaClienteUseCase
    ) {
        return new IniciaPedidoUseCase(iniciaPedidoAdapter, identificaClienteUseCase);
    }
}
