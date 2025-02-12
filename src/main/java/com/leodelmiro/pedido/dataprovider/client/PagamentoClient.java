package com.leodelmiro.pedido.dataprovider.client;

import com.leodelmiro.pedido.dataprovider.client.request.OrdemPagamentoRequest;
import com.leodelmiro.pedido.dataprovider.client.response.OrdemPagamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${external-apis.pagamento.url}")
public interface PagamentoClient {
    @PostMapping("/pagamentos")
    OrdemPagamentoResponse criaOrdem(@RequestBody OrdemPagamentoRequest ordemPagamentoRequest);
}
