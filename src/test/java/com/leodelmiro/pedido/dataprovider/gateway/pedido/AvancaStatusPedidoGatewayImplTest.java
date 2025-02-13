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
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AvancaStatusPedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private AvancaStatusPedidoGatewayImpl avancaStatusPedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAvancar() {
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(1L);
        pedido.setPrecoTotal(BigDecimal.ONE);
        ItemPedido item1 = new ItemPedido();
        item1.setId(1L);
        ItemPedido item2 = new ItemPedido();
        item2.setId(2L);
        pedido.addItens(List.of(item1, item2));

        PedidoEntity pedidoEntitySalvo = new PedidoEntity(1L,
                "123",
                BigDecimal.ONE,
                StatusPedido.EM_PREPARACAO,
                100L,
                "123",
                LocalDateTime.now(),
                LocalDateTime.now());
        pedidoEntitySalvo.addItens(List.of(new ItemPedidoEntity(), new ItemPedidoEntity()));

        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(pedidoEntitySalvo);

        Pedido result = avancaStatusPedidoGateway.avancar(pedido);

        assertEquals(pedidoEntitySalvo.toPedido(), result);
    }
}