package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IniciaPedidoGatewayImplTest {

    @InjectMocks
    private IniciaPedidoGatewayImpl iniciaPedidoGateway;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveIniciar() {
        Pedido pedido = new Pedido();
        PedidoEntity savedPedidoEntity = mock(PedidoEntity.class);

        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(savedPedidoEntity);
        when(savedPedidoEntity.toPedido()).thenReturn(pedido);

        Pedido result = iniciaPedidoGateway.iniciar(pedido);

        verify(pedidoRepository, times(1)).save(any(PedidoEntity.class));
        assertEquals(pedido, result);
    }
}