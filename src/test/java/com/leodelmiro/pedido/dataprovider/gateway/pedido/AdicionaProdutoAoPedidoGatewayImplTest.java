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
import static org.mockito.Mockito.*;

class AdicionaProdutoAoPedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private AdicionaProdutoAoPedidoGatewayImpl adicionaProdutoAoPedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAdicionar() {
        Pedido pedido = mock(Pedido.class);
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        PedidoEntity savedPedidoEntity = mock(PedidoEntity.class);
        Pedido expectedPedido = mock(Pedido.class);

        when(pedidoEntity.toPedido()).thenReturn(expectedPedido);
        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(savedPedidoEntity);
        when(savedPedidoEntity.toPedido()).thenReturn(expectedPedido);

        Pedido result = adicionaProdutoAoPedidoGateway.adicionar(pedido);

        assertEquals(expectedPedido, result);
    }
}