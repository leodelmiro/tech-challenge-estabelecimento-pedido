package com.leodelmiro.pedido.entrypoint.api;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.domain.StatusPedido;
import com.leodelmiro.pedido.core.usecase.pedido.*;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.pedido.entrypoint.api.mapper.PedidoMapper;
import com.leodelmiro.pedido.entrypoint.api.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.pedido.entrypoint.api.request.ItemPedidoRequest;
import com.leodelmiro.pedido.entrypoint.api.response.PedidoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static utils.ProdutoApplicationUtilsTest.getProduto;

class PedidoApiTest {

    @InjectMocks
    private PedidoApi pedidoApi;

    @Mock
    private IniciaPedidoUseCase iniciaPedidoUseCase;

    @Mock
    private ListaPedidosUseCase listaPedidosUseCase;

    @Mock
    private AdicionaProdutoAoPedidoUseCase adicionaProdutoAoPedidoUseCase;

    @Mock
    private BuscaProdutoUseCase buscaProdutoUseCase;

    @Mock
    private RemoveProdutoPedidoUseCase removeProdutoPedidoUseCase;

    @Mock
    private AvancaStatusPedidoUseCase avancaStatusPedidoUseCase;

    @Mock
    private FecharPedidoUseCase fecharPedidoUseCase;

    @Mock
    private PedidoMapper pedidoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
    }

    @Test
    void deveIniciarPedidoComSucesso() {
        String cpf = "12345678900";
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        PedidoResponse pedidoResponseMock = new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PENDENTE_FECHAMENTO,
                0L,
                null,
                null,
                LocalDateTime.now(),
                null);
        when(iniciaPedidoUseCase.iniciar(cpf)).thenReturn(pedido);
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidoResponseMock);

        ResponseEntity<PedidoResponse> response = pedidoApi.inicia(cpf);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(pedidoResponseMock, response.getBody());
    }

    @Test
    void deveListarTodosOsPedidosComSucesso() {
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        Set<PedidoResponse> pedidosMock = Set.of(new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PENDENTE_FECHAMENTO,
                0L,
                null,
                null,
                LocalDateTime.now(),
                null));
        when(listaPedidosUseCase.buscar()).thenReturn(Set.of(pedido));
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidosMock.stream().findFirst().get());

        ResponseEntity<Set<PedidoResponse>> response = pedidoApi.listarTodos();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(pedidosMock, response.getBody());
    }

    @Test
    void deveListaPedidosNaFilaComSucesso() {
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        Set<PedidoResponse> pedidosMock = Set.of(new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PENDENTE_FECHAMENTO,
                0L,
                null,
                null,
                LocalDateTime.now(),
                null));
        when(listaPedidosUseCase.listarPedidosNaFila()).thenReturn(Set.of(pedido));
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidosMock.stream().findFirst().get());

        ResponseEntity<Set<PedidoResponse>> response = pedidoApi.listarPedidosNaFila();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(pedidosMock, response.getBody());
    }

    @Test
    void deveAdicionarProdutoAoPedidoComSucesso() {
        Long pedidoId = 1L;
        AdicionaProdutoAoPedidoRequest request = new AdicionaProdutoAoPedidoRequest(List.of(new ItemPedidoRequest(1L, 1)));
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        pedido.setId(pedidoId);
        pedido.addItem(new ItemPedido(1L, 1));
        PedidoResponse pedidoResponse = new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PENDENTE_FECHAMENTO,
                0L,
                null,
                null,
                LocalDateTime.now(),
                null);
        when(adicionaProdutoAoPedidoUseCase.adicionar(any(), any())).thenReturn(pedido);
        when(buscaProdutoUseCase.buscar(any())).thenReturn(getProduto(1L, "Teste", "Teste"));
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidoResponse);

        ResponseEntity<PedidoResponse> response = pedidoApi.adicionaProduto(pedidoId, request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(pedidoResponse, response.getBody());
    }

    @Test
    void deveRemoverProdutoDoPedidoComSucesso() {
        Long pedidoId = 1L;
        Long produtoId = 2L;
        int quantidade = 1;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        PedidoResponse pedidoResponse = new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PENDENTE_FECHAMENTO,
                0L,
                null,
                null,
                LocalDateTime.now(),
                null);
        when(removeProdutoPedidoUseCase.remover(any(), any(), anyInt())).thenReturn(pedido);
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidoResponse);

        ResponseEntity<PedidoResponse> response = pedidoApi.remove(pedidoId, produtoId, quantidade);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(pedidoResponse, response.getBody());
    }

    @Test
    void deveAvancarStatusDoPedidoComSucesso() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PAGO, BigDecimal.ZERO, 0L);
        PedidoResponse pedidoResponse = new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PAGO,
                1L,
                "123",
                LocalDateTime.now(),
                LocalDateTime.now(),
                null);
        when(avancaStatusPedidoUseCase.avancar(any())).thenReturn(pedido);
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidoResponse);

        ResponseEntity<PedidoResponse> response = pedidoApi.avancaStatus(pedidoId);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(pedidoResponse, response.getBody());
    }

    @Test
    void deveFecharPedidoComSucesso() {
        Long pedidoId = 1L;
        Pedido pedido = new Pedido("60770012019", StatusPedido.PENDENTE_FECHAMENTO, BigDecimal.ZERO, 0L);
        PedidoResponse pedidoResponse = new PedidoResponse(1L,
                "60770012019",
                BigDecimal.ZERO,
                StatusPedido.PENDENTE_FECHAMENTO,
                0L,
                null,
                null,
                LocalDateTime.now(),
                null);
        when(fecharPedidoUseCase.fechar(any())).thenReturn(pedido);
        when(pedidoMapper.toPedidoResponse(any())).thenReturn(pedidoResponse);

        ResponseEntity<PedidoResponse> response = pedidoApi.fecha(pedidoId);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(pedidoResponse, response.getBody());
    }
}