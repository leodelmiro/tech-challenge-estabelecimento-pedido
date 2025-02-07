package com.leodelmiro.pedido.dataprovider.gateway.pedido;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.dataprovider.client.PagamentoClient;
import com.leodelmiro.pedido.dataprovider.client.request.OrdemPagamentoRequest;
import com.leodelmiro.pedido.dataprovider.client.response.OrdemPagamentoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static utils.ProdutoApplicationUtilsTest.getProduto;

class GerarOrdemPagamentoGatewayImplTest {

    @Mock
    private PagamentoClient pagamentoClient;

    @InjectMocks
    private GerarOrdemPagamentoGatewayImpl gerarOrdemPagamentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveGerar() {
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(1L);
        pedido.setPrecoTotal(BigDecimal.valueOf(100.50));
        pedido.addItens(List.of(new ItemPedido(1L, 2), new ItemPedido(2L, 1)));

        Produto produto1 = getProduto(1L, "Produto 1", "Teste");
        Produto produto2 = getProduto(2L, "Produto 2", "Teste");
        Map<Long, Produto> produtos = Map.of(
                1L, produto1,
                2L, produto2
        );

        OrdemPagamentoResponse response = new OrdemPagamentoResponse("12345",
                1L,
                "123",
                3.0,
                LocalDateTime.now(),
                null);
        when(pagamentoClient.criaOrdem(any(OrdemPagamentoRequest.class))).thenReturn(response);

        String result = gerarOrdemPagamentoGateway.gerar(pedido, produtos);

        assertEquals("12345", result);
    }
}