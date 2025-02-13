package com.leodelmiro.pedido.core.usecase.cliente.impl;

import com.leodelmiro.pedido.core.dataprovider.cliente.IdentificaClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IdentificaClienteUseCaseImplTest {

    private IdentificaClienteGateway identificaClienteGateway;
    private IdentificaClienteUseCaseImpl identificaClienteUseCase;

    @BeforeEach
    void setUp() {
        identificaClienteGateway = mock(IdentificaClienteGateway.class);
        identificaClienteUseCase = new IdentificaClienteUseCaseImpl(identificaClienteGateway);
    }

    @Test
    void deveRetornarTrueQuandoIdentificar() {
        String cpf = "12345678900";
        when(identificaClienteGateway.identificar(cpf)).thenReturn(true);

        Boolean result = identificaClienteUseCase.identificar(cpf);

        assertEquals(true, result);
        verify(identificaClienteGateway, times(1)).identificar(cpf);
    }

    @Test
    void deveRetornarFalseQuandoNaoIdentificar() {
        String cpf = "12345678900";
        when(identificaClienteGateway.identificar(cpf)).thenReturn(false);

        Boolean result = identificaClienteUseCase.identificar(cpf);

        assertEquals(false, result);
        verify(identificaClienteGateway, times(1)).identificar(cpf);
    }

    @Test
    void deveRetornarFalseQuandoNull() {
        String cpf = null;
        when(identificaClienteGateway.identificar(cpf)).thenReturn(false);

        Boolean result = identificaClienteUseCase.identificar(cpf);

        assertEquals(false, result);
        verify(identificaClienteGateway, times(1)).identificar(cpf);
    }
}