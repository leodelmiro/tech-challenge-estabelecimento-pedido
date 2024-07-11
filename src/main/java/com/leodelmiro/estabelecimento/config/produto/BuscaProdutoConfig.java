package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.BuscaProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.BuscaProdutoUseCase;
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
