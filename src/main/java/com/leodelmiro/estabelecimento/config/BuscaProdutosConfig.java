package com.leodelmiro.estabelecimento.config;

import com.leodelmiro.estabelecimento.adapters.out.BuscaProdutosAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.BuscaProdutosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaProdutosConfig {

    @Bean
    public BuscaProdutosUseCase buscaProdutosUseCase(
            BuscaProdutosAdapter buscaProdutosAdapter
    ) {
        return new BuscaProdutosUseCase(buscaProdutosAdapter);
    }
}
