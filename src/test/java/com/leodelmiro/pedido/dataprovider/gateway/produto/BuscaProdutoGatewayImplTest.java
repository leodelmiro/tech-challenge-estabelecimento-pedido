package com.leodelmiro.pedido.dataprovider.gateway.produto;

import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.dataprovider.client.ProdutoClient;
import com.leodelmiro.pedido.dataprovider.gateway.mapper.ProdutoMapper;
import com.leodelmiro.pedido.dataprovider.client.response.ProdutoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static utils.ProdutoApplicationUtilsTest.getProdutoResponse;

class BuscaProdutoGatewayImplTest {

    @Mock
    private ProdutoClient produtoClient;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private BuscaProdutoGatewayImpl buscaProdutoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscar() {
        Long produtoId = 1L;
        ProdutoResponse produtoResponse = getProdutoResponse(10);
        Produto expectedProduto = new Produto();

        when(produtoClient.buscaProduto(produtoId)).thenReturn(produtoResponse);
        when(produtoMapper.toProduto(produtoResponse)).thenReturn(expectedProduto);

        Produto actualProduto = buscaProdutoGateway.buscar(produtoId);

        assertEquals(expectedProduto, actualProduto);
        verify(produtoClient, times(1)).buscaProduto(produtoId);
        verify(produtoMapper, times(1)).toProduto(produtoResponse);
    }
}