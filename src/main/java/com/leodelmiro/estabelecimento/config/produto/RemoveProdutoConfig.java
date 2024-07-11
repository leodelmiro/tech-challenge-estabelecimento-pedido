package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.RemoveProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.RemoveProdutoUseCase;
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
