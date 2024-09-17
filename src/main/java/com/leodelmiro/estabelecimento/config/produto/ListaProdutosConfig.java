package com.leodelmiro.estabelecimento.config.produto;

import com.leodelmiro.estabelecimento.dataprovider.gateway.produto.ListaProdutosGatewayImpl;
import com.leodelmiro.estabelecimento.core.usecase.produto.impl.ListaProdutosUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaProdutosConfig {

    @Bean
    public ListaProdutosUseCaseImpl listaProdutosUseCase(
            ListaProdutosGatewayImpl listaProdutosGatewayImpl
    ) {
        return new ListaProdutosUseCaseImpl(listaProdutosGatewayImpl);
    }
}
