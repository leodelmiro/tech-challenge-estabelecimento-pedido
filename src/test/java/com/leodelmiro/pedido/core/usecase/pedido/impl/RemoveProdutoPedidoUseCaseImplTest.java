package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.RemoveProdutoPedidoGateway;
import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static utils.ProdutoApplicationUtilsTest.getProduto;

class RemoveProdutoPedidoUseCaseImplTest {

    @Mock
    private RemoveProdutoPedidoGateway removeProdutoPedidoGateway;

    @Mock
    private BuscaPedidoUseCase buscaPedidoUseCase;

    @Mock
    private BuscaProdutoUseCase buscaProdutoUseCase;

    @InjectMocks
    private RemoveProdutoPedidoUseCaseImpl removeProdutoPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRemoverProdutoDoPedidoQuandoQuantidadeBater() {
        Long idPedido = 1L;
        Long idProduto = 2L;
        int quantidade = 5;

        Produto produto = getProduto(idProduto, "Produto Teste", "Teste");
        ItemPedido itemPedido = new ItemPedido(1L, idProduto, 5);
        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.addItem(itemPedido);

        when(buscaPedidoUseCase.buscar(idPedido)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(idProduto)).thenReturn(produto);
        when(removeProdutoPedidoGateway.remover(pedido)).thenReturn(pedido);

        Pedido result = removeProdutoPedidoUseCase.remover(idPedido, idProduto, quantidade);

        assertTrue(result.getItens().isEmpty());
        verify(removeProdutoPedidoGateway).remover(pedido);
    }

    @Test
    void deveAtualizarAQuantidadeDoPedidoAoRemover() {
        Long idPedido = 1L;
        Long idProduto = 2L;
        int quantidade = 3;

        Produto produto = getProduto(idProduto, "Produto Teste", "Teste");
        ItemPedido itemPedido = new ItemPedido(1L, idProduto, 5);
        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.addItem(itemPedido);

        when(buscaPedidoUseCase.buscar(idPedido)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(idProduto)).thenReturn(produto);
        when(removeProdutoPedidoGateway.remover(pedido)).thenReturn(pedido);

        Pedido result = removeProdutoPedidoUseCase.remover(idPedido, idProduto, quantidade);

        assertEquals(2, result.getItens().getFirst().getQuantidade());
        verify(removeProdutoPedidoGateway).remover(pedido);
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeExcederQuantidadeDoPedido() {
        Long idPedido = 1L;
        Long idProduto = 2L;
        int quantidade = 6;

        Produto produto = getProduto(idProduto, "Produto Teste", "Teste");
        ItemPedido itemPedido = new ItemPedido(1L, idProduto, 5);
        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.addItem(itemPedido);

        when(buscaPedidoUseCase.buscar(idPedido)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(idProduto)).thenReturn(produto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                removeProdutoPedidoUseCase.remover(idPedido, idProduto, quantidade));
        assertEquals("Quantidade maior do que a quantidade no pedido", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoBaterComIdPedido() {
        Long idPedido = 1L;
        Long idProduto = 2L;
        int quantidade = 5;

        Produto produto = getProduto(idProduto, "Produto Teste", "Teste");
        Pedido pedido = new Pedido();
        pedido.setId(idPedido);

        when(buscaPedidoUseCase.buscar(idPedido)).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(idProduto)).thenReturn(produto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                removeProdutoPedidoUseCase.remover(idPedido, idProduto, quantidade));
        assertEquals("Id produto não corresponde a um item do pedido informado", exception.getMessage());
    }
}