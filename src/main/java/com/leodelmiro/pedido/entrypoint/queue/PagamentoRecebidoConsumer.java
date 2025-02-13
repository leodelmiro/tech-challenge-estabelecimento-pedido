package com.leodelmiro.pedido.entrypoint.queue;

import com.leodelmiro.pedido.core.usecase.pedido.PagaPedidoUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PagamentoRecebidoConsumer {

    @Autowired
    PagaPedidoUseCase pagaPedidoUseCase;

    @SqsListener("${amazon.sqs.pagamentoEfetuado}")
    public void escutaPagagamentoEfetuado(String idPedido) {
        System.out.println("Pagamento efetuado para o pedido: " + idPedido);
        pagaPedidoUseCase.pagar(Long.valueOf(idPedido), LocalDateTime.now());
    }

}
