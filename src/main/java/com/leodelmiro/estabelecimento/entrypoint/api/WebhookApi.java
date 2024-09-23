package com.leodelmiro.estabelecimento.entrypoint.api;

import com.leodelmiro.estabelecimento.core.usecase.pedido.PagaPedidoUseCase;
import com.leodelmiro.estabelecimento.entrypoint.api.request.PaymentRequest;
import com.leodelmiro.estabelecimento.entrypoint.controller.WebhookController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Webhook", description = "Endpoints relacionados a Webhook")
@RestController
@RequestMapping("/api/v1/webhooks")
public class WebhookApi {

    @Autowired
    private PagaPedidoUseCase pagaPedidoUseCase;

    @Operation(
            summary = "Notificação de Pagamento",
            description = "Recebe uma notificação de pagamento realizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento realizado")
    })
    @PostMapping("/payments")
    public ResponseEntity<Void> pagamentoRealizado(@RequestHeader(name = "x-signature") String signature,
                                                   @RequestBody PaymentRequest paymentRequest) {

        WebhookController.pagar(signature, paymentRequest, pagaPedidoUseCase);
        return ResponseEntity.ok().build();
    }

}
