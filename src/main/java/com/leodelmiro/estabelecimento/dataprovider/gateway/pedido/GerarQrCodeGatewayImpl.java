package com.leodelmiro.estabelecimento.dataprovider.gateway.pedido;

import com.leodelmiro.estabelecimento.core.dataprovider.pedido.GerarQrCodeGateway;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.dataprovider.client.MercadoPagoClient;
import com.leodelmiro.estabelecimento.dataprovider.client.request.PedidoClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GerarQrCodeGatewayImpl implements GerarQrCodeGateway {

    @Autowired
    MercadoPagoClient client;

    @Value("${external-apis.mercadopago.vendedor-id}")
    private String vendedorId;

    @Value("${external-apis.mercadopago.caixa-id}")
    private String caixaId;

    // TODO PARA O AMBIENTE DE TESTE DA API É FIXO, PORÉM ALTERAR EM PROD PARA GERAÇÃO COM CLIENT ID E SECRET
    @Value("${external-apis.mercadopago.token}")
    private String token;

    @Override
    public String gerar(Pedido pedidoAGerarQrCode) {

        var pedidoRequest = new PedidoClientRequest(pedidoAGerarQrCode);
        var response = client.gerarQrCode(vendedorId, caixaId, "Bearer " + token, pedidoRequest);
        return response.qrData();
    }
}
