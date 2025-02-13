package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscaPedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private BuscaPedidoGatewayImpl buscaPedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarPedidoQuandoEncontrar() {
        Long pedidoId = 1L;
        PedidoEntity pedidoEntity = new PedidoEntity(pedidoId,
                "123",
                BigDecimal.ONE,
                StatusPedido.EM_PREPARACAO,
                100L,
                "123",
                LocalDateTime.now(),
                LocalDateTime.now());
        pedidoEntity.setId(pedidoId);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedidoEntity));

        Pedido actualPedido = buscaPedidoGateway.buscar(pedidoId);

        assertNotNull(actualPedido);
        assertEquals(pedidoId, actualPedido.getId());
        verify(pedidoRepository, times(1)).findById(pedidoId);
    }

    @Test
    void deveLancarExceptionQuandoPedidoNaoEncontrado() {
        Long pedidoId = 1L;
        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            buscaPedidoGateway.buscar(pedidoId);
        });

        assertEquals("Pedido não encontrado", exception.getMessage());
        verify(pedidoRepository, times(1)).findById(pedidoId);
    }
}