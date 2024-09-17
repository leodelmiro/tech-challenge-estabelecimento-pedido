package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.dataprovider.gateway.produto.RemoveProdutoGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.RemoveProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveProdutoConfig {

    @Bean
    public RemoveProdutoUseCaseImpl removeProdutoUseCase(
            RemoveProdutoGatewayImpl removeProdutoGatewayImpl
    ) {
        return new RemoveProdutoUseCaseImpl(removeProdutoGatewayImpl);
    }
}
