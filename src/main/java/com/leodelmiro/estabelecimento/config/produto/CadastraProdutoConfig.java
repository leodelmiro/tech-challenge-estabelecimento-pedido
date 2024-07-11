package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.CadastraProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.CadastraProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastraProdutoConfig {

    @Bean
    public CadastraProdutoUseCase cadastraProdutoUseCase(
            CadastraProdutoAdapter cadastraProdutoAdapter
    ) {
        return new CadastraProdutoUseCase(cadastraProdutoAdapter);
    }
}
