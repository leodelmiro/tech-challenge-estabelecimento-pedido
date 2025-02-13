package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.ItemPedidoEntity;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveProdutoPedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private RemoveProdutoPedidoGatewayImpl removeProdutoPedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverProdutoPedido() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(pedidoId);
        pedido.addItem(new ItemPedido(1L,2L, 1));
        PedidoEntity pedidoEntity = new PedidoEntity(pedido);
        pedidoEntity.addItens(List.of(new ItemPedidoEntity(1L, pedidoEntity, 1L, 1)));

        PedidoEntity updatedPedidoEntity = new PedidoEntity(pedido);
        updatedPedidoEntity.addItens(List.of());

        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(updatedPedidoEntity);

        Pedido result = removeProdutoPedidoGateway.remover(pedido);

        assertNotNull(result);
        assertTrue(result.getItens().isEmpty());
    }

    @Test
    void deveLandcarExcecaoAoReceberErroDoDb() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(pedidoId);

        when(pedidoRepository.save(any(PedidoEntity.class))).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            removeProdutoPedidoGateway.remover(pedido);
        });

        assertEquals("Database error", exception.getMessage());
        verify(pedidoRepository, times(1)).save(any(PedidoEntity.class));
    }
}