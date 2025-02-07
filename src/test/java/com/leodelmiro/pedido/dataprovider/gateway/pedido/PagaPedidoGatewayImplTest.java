package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
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
import static org.mockito.Mockito.*;

class PagaPedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PagaPedidoGatewayImpl pagaPedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void devePagar() {
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.addItens(List.of(new ItemPedido(1L, 2), new ItemPedido(2L, 1)));
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        pagaPedidoGateway.pagar(pedido);

        ArgumentCaptor<PedidoEntity> pedidoEntityCaptor = ArgumentCaptor.forClass(PedidoEntity.class);
        verify(pedidoRepository, times(1)).save(pedidoEntityCaptor.capture());

        PedidoEntity savedPedidoEntity = pedidoEntityCaptor.getValue();
        assertEquals(pedido.getId(), savedPedidoEntity.getId());
    }
}