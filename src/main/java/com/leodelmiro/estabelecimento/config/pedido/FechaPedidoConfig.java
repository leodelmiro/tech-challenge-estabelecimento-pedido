package com.leodelmiro.estabelecimento.config.pedido;

import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.core.usecase.pedido.impl.FecharPedidoUseCaseImpl;
import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.FecharPedidoGatewayImpl;
import com.leodelmiro.estabelecimento.dataprovider.gateway.pedido.GerarQrCodeGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FechaPedidoConfig {

    @Bean
    public FecharPedidoUseCaseImpl fecharPedidoUseCase(
            FecharPedidoGatewayImpl fecharPedidoGateway,
            GerarQrCodeGatewayImpl gerarQrCodeGateway,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl
    ) {
        return new FecharPedidoUseCaseImpl(fecharPedidoGateway, gerarQrCodeGateway, buscaPedidoUseCaseImpl);
    }
}
