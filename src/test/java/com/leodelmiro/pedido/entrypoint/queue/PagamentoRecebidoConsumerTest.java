package com.leodelmiro.pedido.entrypoint.queue;

import com.leodelmiro.pedido.core.usecase.pedido.PagaPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PagamentoRecebidoConsumerTest {

    @Mock
    private PagaPedidoUseCase pagaPedidoUseCase;

    @InjectMocks
    private PagamentoRecebidoConsumer pagamentoRecebidoConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEscutarPagamentoEfetuado() {
        String idPedido = "12345";
        ArgumentCaptor<Long> idPedidoCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<LocalDateTime> dateTimeCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        pagamentoRecebidoConsumer.escutaPagagamentoEfetuado(idPedido);

        verify(pagaPedidoUseCase, times(1)).pagar(idPedidoCaptor.capture(), dateTimeCaptor.capture());
        assertEquals(Long.valueOf(idPedido), idPedidoCaptor.getValue());
        assertEquals(LocalDateTime.now().getDayOfYear(), dateTimeCaptor.getValue().getDayOfYear());
    }
}