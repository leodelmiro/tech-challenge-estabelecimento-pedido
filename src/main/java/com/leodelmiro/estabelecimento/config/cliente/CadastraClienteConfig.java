package com.leodelmiro.estabelecimento.config.cliente;

import com.leodelmiro.estabelecimento.adapters.out.adapters.cliente.CadastraClienteAdapter;
import com.leodelmiro.estabelecimento.application.core.usecase.cliente.CadastraClienteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastraClienteConfig {

    @Bean
    public CadastraClienteUseCase cadastraClienteUseCase(
            CadastraClienteAdapter cadastraClienteAdapter
    ) {
        return new CadastraClienteUseCase(cadastraClienteAdapter);
    }
}
