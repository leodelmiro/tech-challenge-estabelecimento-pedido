package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.dataprovider.gateway.produto.CadastraProdutoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.CadastraProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastraProdutoConfig {

    @Bean
    public CadastraProdutoUseCaseImpl cadastraProdutoUseCase(
            CadastraProdutoGatewayImpl cadastraProdutoGatewayImpl
    ) {
        return new CadastraProdutoUseCaseImpl(cadastraProdutoGatewayImpl);
    }
}
