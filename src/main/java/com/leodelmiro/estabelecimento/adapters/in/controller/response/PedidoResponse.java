package com.leodelmiro.estabelecimento.adapters.in.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import com.leodelmiro.estabelecimento.application.core.domain.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PedidoResponse(
        Long id,
        Cliente cliente,
        BigDecimal precoTotal,
        StatusPedido status,
        Long tempoTotalDePreparoEmSegundos,
        LocalDateTime pagoEm,
        LocalDateTime criadoEm,
        List<ItemPedidoResponse> itens
) {
}
