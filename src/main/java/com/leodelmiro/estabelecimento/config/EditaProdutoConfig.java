package com.leodelmiro.estabelecimento.config;

import com.leodelmiro.estabelecimento.adapters.out.EditaProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.BuscaProdutoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.EditaProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EditaProdutoConfig {

    @Bean
    public EditaProdutoUseCase editaProdutoUseCase(
            EditaProdutoAdapter editaProdutoAdapter,
            BuscaProdutoUseCase buscaProdutoUseCase
    ) {
        return new EditaProdutoUseCase(editaProdutoAdapter, buscaProdutoUseCase);
    }
}
