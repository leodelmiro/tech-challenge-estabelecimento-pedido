package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.adapters.out.adapters.produto.EditaProdutoAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.estabelecimento.application.core.usecase.produto.EditaProdutoUseCase;
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
