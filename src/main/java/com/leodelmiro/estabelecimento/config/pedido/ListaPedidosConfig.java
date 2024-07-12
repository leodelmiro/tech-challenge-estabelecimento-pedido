package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.adapters.out.adapters.pedido.ListaPedidosAdapter;
import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.ListaProdutosAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.pedido.ListaPedidosUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.ListaProdutosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaPedidosConfig {

    @Bean
    public ListaPedidosUseCase listaPedidosUseCase(
            ListaPedidosAdapter listaPedidosAdapter
    ) {
        return new ListaPedidosUseCase(listaPedidosAdapter);
    }
}
