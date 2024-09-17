package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.dataprovider.gateway.produto.BuscaProdutoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaProdutoConfig {

    @Bean
    public BuscaProdutoUseCaseImpl buscaProdutoUseCase(
            BuscaProdutoGatewayImpl buscaProdutoGatewayImpl
    ) {
        return new BuscaProdutoUseCaseImpl(buscaProdutoGatewayImpl);
    }
}
