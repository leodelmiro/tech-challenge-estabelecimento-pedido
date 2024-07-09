package com.leodelmiro.estabelecimento.config;

import com.leodelmiro.estabelecimento.adapters.out.CriaProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.CriaProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriaProdutoConfig {

    @Bean
    public CriaProdutoUseCase criaProdutoUseCase(
            CriaProdutoAdapter criaProdutoAdapter
    ) {
        return new CriaProdutoUseCase(criaProdutoAdapter);
    }
}
