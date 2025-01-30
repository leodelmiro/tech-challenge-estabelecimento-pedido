package com.leodelmiro.pedido.dataprovider.client;

import com.leodelmiro.pedido.dataprovider.client.request.OrdemPagamentoRequest;
import com.leodelmiro.pedido.dataprovider.client.response.OrdemPagamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "pagamentoClient", url = "${external-apis.pagamento.url}")
public interface PagamentoClient {
    @PostMapping("/pagamentos")
    OrdemPagamentoResponse criaOrdem(@PathVariable OrdemPagamentoRequest ordemPagamentoRequest);
}
