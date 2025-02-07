package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.BuscaPedidoGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class BuscaPedidoUseCaseImplTest {

    @Test
    void deveBuscarIdValido() {
        Long pedidoId = 1L;
        Pedido expectedPedido = new Pedido(); // Assuming Pedido is a valid class
        BuscaPedidoGateway buscaPedidoGatewayMock = mock(BuscaPedidoGateway.class);
        when(buscaPedidoGatewayMock.buscar(pedidoId)).thenReturn(expectedPedido);

        BuscaPedidoUseCaseImpl buscaPedidoUseCase = new BuscaPedidoUseCaseImpl(buscaPedidoGatewayMock);

        Pedido actualPedido = buscaPedidoUseCase.buscar(pedidoId);

        assertEquals(expectedPedido, actualPedido, "The returned Pedido should match the expected Pedido");
        verify(buscaPedidoGatewayMock, times(1)).buscar(pedidoId);
    }

    @Test
    void deveRetornarNullComIdInvalido() {
        Long invalidPedidoId = 999L;
        BuscaPedidoGateway buscaPedidoGatewayMock = mock(BuscaPedidoGateway.class);
        when(buscaPedidoGatewayMock.buscar(invalidPedidoId)).thenReturn(null);

        BuscaPedidoUseCaseImpl buscaPedidoUseCase = new BuscaPedidoUseCaseImpl(buscaPedidoGatewayMock);

        Pedido actualPedido = buscaPedidoUseCase.buscar(invalidPedidoId);

        assertNull(actualPedido);
        verify(buscaPedidoGatewayMock, times(1)).buscar(invalidPedidoId);
    }

    @Test
    void deveRetornarNullAoPassarNull() {
        Long nullId = null;
        BuscaPedidoGateway buscaPedidoGatewayMock = mock(BuscaPedidoGateway.class);
        when(buscaPedidoGatewayMock.buscar(nullId)).thenReturn(null);

        BuscaPedidoUseCaseImpl buscaPedidoUseCase = new BuscaPedidoUseCaseImpl(buscaPedidoGatewayMock);

        Pedido actualPedido = buscaPedidoUseCase.buscar(nullId);

        assertNull(actualPedido);
        verify(buscaPedidoGatewayMock, times(1)).buscar(nullId);
    }
}