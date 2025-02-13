package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.IniciaPedidoGateway;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.core.usecase.cliente.IdentificaClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IniciaPedidoUseCaseImplTest {

    private IniciaPedidoGateway iniciaPedidoGateway;
    private IdentificaClienteUseCase identificaClienteUseCase;
    private IniciaPedidoUseCaseImpl iniciaPedidoUseCase;

    @BeforeEach
    void setUp() {
        iniciaPedidoGateway = mock(IniciaPedidoGateway.class);
        identificaClienteUseCase = mock(IdentificaClienteUseCase.class);
        iniciaPedidoUseCase = new IniciaPedidoUseCaseImpl(iniciaPedidoGateway, identificaClienteUseCase);
    }

    @Test
    void deveIniciarPedidoComCpfNull() {
        String cpf = null;
        Pedido expectedPedido = new Pedido(cpf, StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        when(iniciaPedidoGateway.iniciar(any(Pedido.class))).thenReturn(expectedPedido);

        Pedido result = iniciaPedidoUseCase.iniciar(cpf);

        assertNotNull(result);
        assertEquals(expectedPedido, result);
    }

    @Test
    void deveIniciarPedidoComCpf() {
        String cpf = "12345678900";
        Pedido expectedPedido = new Pedido(cpf, StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        when(identificaClienteUseCase.identificar(cpf)).thenReturn(true);
        when(iniciaPedidoGateway.iniciar(any(Pedido.class))).thenReturn(expectedPedido);

        Pedido result = iniciaPedidoUseCase.iniciar(cpf);

        assertNotNull(result);
        assertEquals(expectedPedido, result);
    }

    @Test
    void deveLancarExcecaoQuandoCLienteNaoExistente() {
        String cpf = "12345678900";
        when(identificaClienteUseCase.identificar(cpf)).thenReturn(false);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> iniciaPedidoUseCase.iniciar(cpf));
        assertEquals("CPF precisa ser null ou Cliente existente", exception.getMessage());

        verifyNoInteractions(iniciaPedidoGateway);
    }
}