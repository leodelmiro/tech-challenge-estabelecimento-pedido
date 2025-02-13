package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.dataprovider.pedido.GerarOrdemPagamentoGateway;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.dataprovider.client.PagamentoClient;
import com.leodelmiro.pedido.dataprovider.client.request.ItemPedidoRequest;
import com.leodelmiro.pedido.dataprovider.client.request.OrdemPagamentoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GerarOrdemPagamentoGatewayImpl implements GerarOrdemPagamentoGateway {

    @Autowired
    PagamentoClient client;

    @Override
    public String gerar(Pedido pedidoAGerarQrCode, Map<Long, Produto> produtos) {

        var pedidoRequest = new OrdemPagamentoRequest(
                pedidoAGerarQrCode.getId().toString(),
                pedidoAGerarQrCode.getPrecoTotal().doubleValue(),
                pedidoAGerarQrCode.getItens().stream().map(itemPedido -> new ItemPedidoRequest(itemPedido, produtos.get(itemPedido.getProdutoId()))).toList()
        );
        var response = client.criaOrdem(pedidoRequest);
        return response.id();
    }
}
