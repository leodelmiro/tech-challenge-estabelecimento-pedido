package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.FecharPedidoGateway;
import com.leodelmiro.pedido.core.dataprovider.pedido.GerarOrdemPagamentoGateway;
import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Map;

import static com.leodelmiro.pedido.core.domain.StatusPedido.PENDENTE_FECHAMENTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FecharPedidoUseCaseImplTest {

    @Mock
    private FecharPedidoGateway fecharPedidoGateway;

    @Mock
    private GerarOrdemPagamentoGateway gerarQrCodeGateway;

    @Mock
    private BuscaPedidoUseCase buscaPedidoUseCase;

    @Mock
    private BuscaProdutoUseCase buscaProdutoUseCase;

    @InjectMocks
    private FecharPedidoUseCaseImpl fecharPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveFecharPedidoComSucesso() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(pedidoId);
        pedido.setStatus(PENDENTE_FECHAMENTO);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdProduto(1L);
        pedido.addItem(itemPedido);

        Produto produto = new Produto();
        produto.setId(1L);

        when(buscaPedidoUseCase.buscar(pedidoId)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(1L)).thenReturn(produto);
        when(gerarQrCodeGateway.gerar(eq(pedido), any(Map.class))).thenReturn("qrCodeId");
        when(fecharPedidoGateway.fechar(pedido)).thenReturn(pedido);

        Pedido result = fecharPedidoUseCase.fechar(pedidoId);

        assertNotNull(result);
        assertEquals("qrCodeId", result.getOrdemPagamentoId());
        verify(buscaPedidoUseCase).buscar(pedidoId);
        verify(buscaProdutoUseCase).buscar(1L);
        verify(gerarQrCodeGateway).gerar(eq(pedido), any(Map.class));
        verify(fecharPedidoGateway).fechar(pedido);
    }

    @Test
    void deveLancarExcecaoQuandoTentarFecharPedidoSemItens() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(pedidoId);
        pedido.setStatus(PENDENTE_FECHAMENTO);

        when(buscaPedidoUseCase.buscar(pedidoId)).thenReturn(pedido);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fecharPedidoUseCase.fechar(pedidoId));
        assertEquals("Impossível fechar pedido sem itens", exception.getMessage());
        verify(buscaPedidoUseCase).buscar(pedidoId);
        verifyNoInteractions(buscaProdutoUseCase, gerarQrCodeGateway, fecharPedidoGateway);
    }

    @Test
    void deveLancarExcecaoQuandoTentarFecharPedidoQueNaoEstejaPendenteFechamento() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(null);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdProduto(1L);
        pedido.addItem(itemPedido);

        when(buscaPedidoUseCase.buscar(pedidoId)).thenReturn(pedido);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fecharPedidoUseCase.fechar(pedidoId));
        assertEquals("Impossível avancar pedido com status diferente de Pendente de Fechamento", exception.getMessage());
        verify(buscaPedidoUseCase).buscar(pedidoId);
        verifyNoInteractions(buscaProdutoUseCase, gerarQrCodeGateway, fecharPedidoGateway);
    }

    @Test
    void deveValidarOrdemPagamentoGerada() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);
        pedido.setId(pedidoId);
        pedido.setStatus(PENDENTE_FECHAMENTO);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdProduto(1L);
        pedido.addItem(itemPedido);

        Produto produto = new Produto();
        produto.setId(1L);

        when(buscaPedidoUseCase.buscar(pedidoId)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(1L)).thenReturn(produto);
        when(gerarQrCodeGateway.gerar(eq(pedido), any(Map.class))).thenReturn("generatedOrderId");
        when(fecharPedidoGateway.fechar(pedido)).thenReturn(pedido);

        Pedido result = fecharPedidoUseCase.fechar(pedidoId);

        assertNotNull(result);
        assertEquals("generatedOrderId", result.getOrdemPagamentoId());
        verify(gerarQrCodeGateway).gerar(eq(pedido), any(Map.class));
    }
}