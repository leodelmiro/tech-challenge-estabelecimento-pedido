package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListaPedidosGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ListaPedidosGatewayImpl listaPedidosGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodosOsPedidos() {
        PedidoEntity pedidoEntity1 = mock(PedidoEntity.class);
        PedidoEntity pedidoEntity2 = mock(PedidoEntity.class);
        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);

        when(pedidoEntity1.toPedido()).thenReturn(pedido1);
        when(pedidoEntity2.toPedido()).thenReturn(pedido2);
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedidoEntity1, pedidoEntity2));

        Set<Pedido> result = listaPedidosGateway.listarTodos();

        assertEquals(2, result.size());
        verify(pedidoRepository, times(1)).findAll();
        verify(pedidoEntity1, times(1)).toPedido();
        verify(pedidoEntity2, times(1)).toPedido();
    }

    @Test
    void deveListarTodosPedidosNaFila() {
        PedidoEntity pedidoEntity1 = mock(PedidoEntity.class);
        PedidoEntity pedidoEntity2 = mock(PedidoEntity.class);
        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);

        when(pedidoEntity1.toPedido()).thenReturn(pedido1);
        when(pedidoEntity2.toPedido()).thenReturn(pedido2);
        when(pedidoRepository.findPedidosPagosEPendentesDeFinalizacao()).thenReturn(Arrays.asList(pedidoEntity1, pedidoEntity2));

        Set<Pedido> result = listaPedidosGateway.listarPedidosNaFila();

        assertEquals(2, result.size());
        verify(pedidoRepository, times(1)).findPedidosPagosEPendentesDeFinalizacao();
        verify(pedidoEntity1, times(1)).toPedido();
        verify(pedidoEntity2, times(1)).toPedido();
    }

    @Test
    void deveRetornarVazioSeNaoHouvePedidos() {
        when(pedidoRepository.findAll()).thenReturn(List.of());

        Set<Pedido> result = listaPedidosGateway.listarTodos();

        assertEquals(0, result.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarVazioSeNaoHouverPedidosNaFila() {
        when(pedidoRepository.findPedidosPagosEPendentesDeFinalizacao()).thenReturn(List.of());

        Set<Pedido> result = listaPedidosGateway.listarPedidosNaFila();

        assertEquals(0, result.size());
        verify(pedidoRepository, times(1)).findPedidosPagosEPendentesDeFinalizacao();
    }
}