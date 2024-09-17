package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.dataprovider.gateway.produto.EditaProdutoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.EditaProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EditaProdutoConfig {

    @Bean
    public EditaProdutoUseCaseImpl editaProdutoUseCase(
            EditaProdutoGatewayImpl editaProdutoGatewayImpl,
            BuscaProdutoUseCaseImpl buscaProdutoUseCaseImpl
    ) {
        return new EditaProdutoUseCaseImpl(editaProdutoGatewayImpl, buscaProdutoUseCaseImpl);
    }
}
