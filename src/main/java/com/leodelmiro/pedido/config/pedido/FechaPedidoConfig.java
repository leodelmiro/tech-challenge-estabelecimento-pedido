package com.leodelmiro.pedido.config.pedido;

import com.leodelmiro.pedido.core.usecase.pedido.impl.BuscaPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.pedido.impl.FecharPedidoUseCaseImpl;
import com.leodelmiro.pedido.core.usecase.produto.impl.BuscaProdutoUseCaseImpl;
import com.leodelmiro.pedido.dataprovider.gateway.pedido.FecharPedidoGatewayImpl;
import com.leodelmiro.pedido.dataprovider.gateway.pedido.GerarOrdemPagamentoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FechaPedidoConfig {

    @Bean
    public FecharPedidoUseCaseImpl fecharPedidoUseCase(
            FecharPedidoGatewayImpl fecharPedidoGatewayImpl,
            GerarOrdemPagamentoGatewayImpl gerarQrCodeGatewayImpl,
            BuscaPedidoUseCaseImpl buscaPedidoUseCaseImpl,
            BuscaProdutoUseCaseImpl buscaProdutoUseCaseImpl
    ) {
        return new FecharPedidoUseCaseImpl(
                fecharPedidoGatewayImpl,
                gerarQrCodeGatewayImpl,
                buscaPedidoUseCaseImpl,
                buscaProdutoUseCaseImpl);
    }
}
