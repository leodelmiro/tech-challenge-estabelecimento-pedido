package com.leodelmiro.estabelecimento.config;

import com.leodelmiro.estabelecimento.adapters.out.BuscaProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.BuscaProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaProdutoConfig {

    @Bean
    public BuscaProdutoUseCase buscaProdutoUseCase(
            BuscaProdutoAdapter buscaProdutoAdapter
    ) {
        return new BuscaProdutoUseCase(buscaProdutoAdapter);
    }
}
