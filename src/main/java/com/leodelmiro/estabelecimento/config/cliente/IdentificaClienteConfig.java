package com.leodelmiro.estabelecimento.config.cliente;

import com.leodelmiro.estabelecimento.adapters.out.adapters.cliente.IdentificaClienteAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.cliente.IdentificaClienteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentificaClienteConfig {

    @Bean
    public IdentificaClienteUseCase identificaClienteUseCase(
            IdentificaClienteAdapter identificaClienteAdapter
    ) {
        return new IdentificaClienteUseCase(identificaClienteAdapter);
    }
}
