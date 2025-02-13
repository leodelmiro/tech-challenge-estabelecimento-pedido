package com.leodelmiro.pedido.dataprovider.gateway.cliente;

import com.leodelmiro.pedido.dataprovider.client.ClienteClient;
import com.leodelmiro.pedido.dataprovider.client.response.ClienteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class IdentificaClienteGatewayImplTest {

    private IdentificaClienteGatewayImpl identificaClienteGateway;

    @Mock
    private ClienteClient clienteClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        identificaClienteGateway = new IdentificaClienteGatewayImpl();
        ReflectionTestUtils.setField(identificaClienteGateway, "clienteClient", clienteClient);
    }

    @Test
    void deveIdentificarClienteQuandoExistir() {
        String cpf = "12345678900";
        when(clienteClient.buscaCliente(cpf)).thenReturn(new ClienteResponse("12345678900", "Teste")); // Mocking a non-null response

        Boolean result = identificaClienteGateway.identificar(cpf);

        assertTrue(result);
    }

    @Test
    void deveRetornarNullQuandoNaoExistir() {
        String cpf = "12345678900";
        when(clienteClient.buscaCliente(cpf)).thenReturn(null);

        Boolean result = identificaClienteGateway.identificar(cpf);

        assertFalse(result);
    }
}