package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.PagaPedidoGateway;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.pedido.core.domain.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PagaPedidoUseCaseImplTest {

    private PagaPedidoGateway pagaPedidoGateway;
    private BuscaPedidoUseCase buscaPedidoUseCase;
    private PagaPedidoUseCaseImpl pagaPedidoUseCase;

    @BeforeEach
    void setUp() {
        pagaPedidoGateway = mock(PagaPedidoGateway.class);
        buscaPedidoUseCase = mock(BuscaPedidoUseCase.class);
        pagaPedidoUseCase = new PagaPedidoUseCaseImpl(pagaPedidoGateway, buscaPedidoUseCase);
    }

    @Test
    void devePagarPedidoEAvancarStatus() {
        Long idPedido = 1L;
        LocalDateTime pagoEm = LocalDateTime.now();
        Pedido pedidoMock = mock(Pedido.class);

        when(buscaPedidoUseCase.buscar(idPedido)).thenReturn(pedidoMock);

        pagaPedidoUseCase.pagar(idPedido, pagoEm);

        verify(buscaPedidoUseCase).buscar(idPedido);
        verify(pedidoMock).setPagoEm(pagoEm);
        verify(pedidoMock).avancarStatus();
        verify(pagaPedidoGateway).pagar(pedidoMock);
    }
}