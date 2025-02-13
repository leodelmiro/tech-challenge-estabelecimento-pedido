package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.AdicionaProdutoAoPedidoGateway;
import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static utils.ProdutoApplicationUtilsTest.getProduto;

class AdicionaProdutoAoPedidoUseCaseImplTest {

    private AdicionaProdutoAoPedidoGateway adicionaProdutoAoPedidoGateway;
    private BuscaPedidoUseCase buscaPedidoUseCase;
    private BuscaProdutoUseCase buscaProdutoUseCase;
    private AdicionaProdutoAoPedidoUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        adicionaProdutoAoPedidoGateway = mock(AdicionaProdutoAoPedidoGateway.class);
        buscaPedidoUseCase = mock(BuscaPedidoUseCase.class);
        buscaProdutoUseCase = mock(BuscaProdutoUseCase.class);
        useCase = new AdicionaProdutoAoPedidoUseCaseImpl(adicionaProdutoAoPedidoGateway, buscaPedidoUseCase, buscaProdutoUseCase);
    }

    @Test
    void deveAdicionar() {
        Long idPedido = 1L;
        ItemPedido item1 = new ItemPedido(1L, 2);
        ItemPedido item2 = new ItemPedido(2L, 3);
        List<ItemPedido> itens = List.of(item1, item2);

        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ONE, 0L);

        Produto produto1 = getProduto(1L, "Produto 1", "Teste");
        Produto produto2 = getProduto(2L, "Produto 2", "Teste");

        when(buscaPedidoUseCase.buscar(idPedido)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(1L)).thenReturn(produto1);
        when(buscaProdutoUseCase.buscar(2L)).thenReturn(produto2);
        when(adicionaProdutoAoPedidoGateway.adicionar(Mockito.any(Pedido.class))).thenReturn(pedido);

        Pedido result = useCase.adicionar(idPedido, itens);

        assertEquals(pedido, result);
        assertEquals(new BigDecimal(5.00).setScale(2), pedido.getPrecoTotal());
        assertEquals(5L, pedido.getTempoTotalDePreparoEmSegundos());
    }
}