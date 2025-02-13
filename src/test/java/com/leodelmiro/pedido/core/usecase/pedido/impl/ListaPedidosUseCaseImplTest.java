package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.dataprovider.pedido.ListaPedidosGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListaPedidosUseCaseImplTest {

    private ListaPedidosGateway listaPedidosGateway;
    private ListaPedidosUseCaseImpl listaPedidosUseCase;

    @BeforeEach
    void setUp() {
        listaPedidosGateway = Mockito.mock(ListaPedidosGateway.class);
        listaPedidosUseCase = new ListaPedidosUseCaseImpl(listaPedidosGateway);
    }

    @Test
    void deveListarPedidos() {
        Set<Pedido> pedidosEsperados = new HashSet<>();
        pedidosEsperados.add(new Pedido());
        when(listaPedidosGateway.listarTodos()).thenReturn(pedidosEsperados);

        Set<Pedido> result = listaPedidosUseCase.buscar();

        assertEquals(pedidosEsperados, result);
        verify(listaPedidosGateway, times(1)).listarTodos();
    }

    @Test
    void deveListarPedidosNaFila() {
        Set<Pedido> pedidosEsperadosNaFila = new HashSet<>();
        pedidosEsperadosNaFila.add(new Pedido());
        when(listaPedidosGateway.listarPedidosNaFila()).thenReturn(pedidosEsperadosNaFila);

        Set<Pedido> result = listaPedidosUseCase.listarPedidosNaFila();

        assertEquals(pedidosEsperadosNaFila, result);
        verify(listaPedidosGateway, times(1)).listarPedidosNaFila();
    }
}