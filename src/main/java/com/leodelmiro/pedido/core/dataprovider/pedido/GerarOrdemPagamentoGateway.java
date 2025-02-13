package com.leodelmiro.pedido.core.dataprovider.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;

import java.util.Map;

public interface GerarOrdemPagamentoGateway {
    String gerar(Pedido pedido, Map<Long, Produto> produto);
}
