package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.AvancaStatusPedidoGateway;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvancaStatusPedidoUseCaseImplTest {

    private AvancaStatusPedidoGateway avancaStatusPedidoGateway;
    private BuscaPedidoUseCase buscaPedidoUseCase;
    private AvancaStatusPedidoUseCaseImpl avancaStatusPedidoUseCase;

    @BeforeEach
    void setUp() {
        avancaStatusPedidoGateway = mock(AvancaStatusPedidoGateway.class);
        buscaPedidoUseCase = mock(BuscaPedidoUseCase.class);
        avancaStatusPedidoUseCase = new AvancaStatusPedidoUseCaseImpl(avancaStatusPedidoGateway, buscaPedidoUseCase);
    }

    @Test
    void deveAvancarPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.EM_PREPARACAO);
        when(buscaPedidoUseCase.buscar(1L)).thenReturn(pedido);
        when(avancaStatusPedidoGateway.avancar(pedido)).thenReturn(pedido);

        Pedido result = avancaStatusPedidoUseCase.avancar(1L);

        assertNotNull(result);
        verify(buscaPedidoUseCase, times(1)).buscar(1L);
        verify(avancaStatusPedidoGateway, times(1)).avancar(pedido);
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
    }

    @Test
    void deveLancarExcecaoQuandoPendenteFechamentoPeloAvancar() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.PENDENTE_FECHAMENTO);
        when(buscaPedidoUseCase.buscar(1L)).thenReturn(pedido);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> avancaStatusPedidoUseCase.avancar(1L));
        assertEquals("Pedidos a fechar devem ser feito pelo Fechar Pedido", exception.getMessage());
        verify(buscaPedidoUseCase, times(1)).buscar(1L);
        verify(avancaStatusPedidoGateway, never()).avancar(pedido);
    }

    @Test
    void deveLancarExcecaoQuandoJaEstiverFinalizado() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.FINALIZADO);
        when(buscaPedidoUseCase.buscar(1L)).thenReturn(pedido);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> avancaStatusPedidoUseCase.avancar(1L));
        assertEquals("Impossível avancar pedido já finalizado", exception.getMessage());
        verify(buscaPedidoUseCase, times(1)).buscar(1L);
        verify(avancaStatusPedidoGateway, never()).avancar(pedido);
    }
}