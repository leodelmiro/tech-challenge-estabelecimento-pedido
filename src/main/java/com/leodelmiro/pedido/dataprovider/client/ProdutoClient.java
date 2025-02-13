package com.leodelmiro.pedido.dataprovider.client;

import com.leodelmiro.pedido.dataprovider.client.response.ProdutoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtoClient", url = "${external-apis.produto.url}")
public interface ProdutoClient {
    @GetMapping("/produtos/{produtoId}")
    ProdutoResponse buscaProduto(@PathVariable Long produtoId);
}
