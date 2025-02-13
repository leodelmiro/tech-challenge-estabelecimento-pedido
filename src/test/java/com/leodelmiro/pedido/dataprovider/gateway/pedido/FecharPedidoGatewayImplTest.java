package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.dataprovider.repository.PedidoRepository;
import com.leodelmiro.pedido.dataprovider.repository.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FecharPedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private FecharPedidoGatewayImpl fecharPedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveFechar() {
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(1L);
        pedido.addItens(List.of(
                new ItemPedido(1L,  2L,2),
                new ItemPedido(2L, 2L, 1)
        ));

        PedidoEntity pedidoEntity = new PedidoEntity(pedido);
        pedidoEntity.setId(1L);

        PedidoEntity savedPedidoEntity = new PedidoEntity(pedido);
        savedPedidoEntity.setId(1L);

        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(savedPedidoEntity);

        Pedido result = fecharPedidoGateway.fechar(pedido);

        ArgumentCaptor<PedidoEntity> pedidoEntityCaptor = ArgumentCaptor.forClass(PedidoEntity.class);
        verify(pedidoRepository, times(1)).save(pedidoEntityCaptor.capture());

        PedidoEntity capturedPedidoEntity = pedidoEntityCaptor.getValue();
        assertEquals(1L, capturedPedidoEntity.getId());
        assertEquals(2, capturedPedidoEntity.getItens().size());
        assertEquals(1L, result.getId());
    }
}