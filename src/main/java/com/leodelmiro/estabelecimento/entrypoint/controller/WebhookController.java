package com.leodelmiro.estabelecimento.entrypoint.controller;

import com.leodelmiro.estabelecimento.core.usecase.pedido.PagaPedidoUseCase;
import com.leodelmiro.estabelecimento.entrypoint.api.request.PaymentRequest;

public class WebhookController {

    public static void pagar(String signature,
                             PaymentRequest paymentRequest,
                             PagaPedidoUseCase pagaPedidoUseCase) {
        pagaPedidoUseCase.pagar(Long.valueOf(paymentRequest.data().id()),
                paymentRequest.createdAt().toLocalDateTime());
    }
}
