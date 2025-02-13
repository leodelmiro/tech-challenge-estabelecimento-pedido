package com.leodelmiro.pedido.dataprovider.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record OrdemPagamentoResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("id_pedido")
        Long idPedido,
        @JsonProperty("qr_code")
        String qrCode,
        @JsonProperty("preco_total")
        Double precoTotal,
        @JsonProperty("criado_em")
        LocalDateTime criadoEm,
        @JsonProperty("pago_em")
        LocalDateTime pagoEm
) {}
