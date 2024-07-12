package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.ListaProdutosAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.ListaProdutosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaProdutosConfig {

    @Bean
    public ListaProdutosUseCase buscaProdutosUseCase(
            ListaProdutosAdapter listaProdutosAdapter
    ) {
        return new ListaProdutosUseCase(listaProdutosAdapter);
    }
}
