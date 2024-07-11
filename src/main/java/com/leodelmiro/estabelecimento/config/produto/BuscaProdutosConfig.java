package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.BuscaProdutosAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.BuscaProdutosUseCase;
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
