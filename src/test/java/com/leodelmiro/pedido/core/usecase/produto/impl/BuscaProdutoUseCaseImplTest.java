package com.leodelmiro.pedido.core.usecase.produto.impl;

import com.leodelmiro.pedido.core.dataprovider.produto.BuscaProdutoGateway;
import com.leodelmiro.pedido.core.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuscaProdutoUseCaseImplTest {

    @Mock
    private BuscaProdutoGateway buscaProdutoGateway;

    @InjectMocks
    private BuscaProdutoUseCaseImpl buscaProdutoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarProdutoExistente() {
        Long id = 1L;
        Produto expectedProduto = new Produto();
        expectedProduto.setId(id);
        expectedProduto.setNome("Produto Teste");

        when(buscaProdutoGateway.buscar(id)).thenReturn(expectedProduto);

        Produto result = buscaProdutoUseCase.buscar(id);

        assertEquals(expectedProduto, result);
        verify(buscaProdutoGateway, times(1)).buscar(id);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistente() {
        Long id = 1L;

        when(buscaProdutoGateway.buscar(id)).thenThrow(new RuntimeException("Produto not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> buscaProdutoUseCase.buscar(id));
        assertEquals("Produto not found", exception.getMessage());
        verify(buscaProdutoGateway, times(1)).buscar(id);
    }
}