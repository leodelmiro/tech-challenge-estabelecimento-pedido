package com.leodelmiro.estabelecimento.config;

import com.leodelmiro.estabelecimento.adapters.out.RemoveProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.RemoveProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveProdutoConfig {

    @Bean
    public RemoveProdutoUseCase removeProdutoUseCase(
            RemoveProdutoAdapter removeProdutoAdapter
    ) {
        return new RemoveProdutoUseCase(removeProdutoAdapter);
    }
}
