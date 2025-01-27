package com.leodelmiro.pedido.entrypoint.api;

import com.leodelmiro.pedido.core.usecase.pedido.*;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.pedido.entrypoint.api.mapper.PedidoMapper;
import com.leodelmiro.pedido.entrypoint.api.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.pedido.entrypoint.api.response.PedidoResponse;
import com.leodelmiro.pedido.entrypoint.controller.PedidoController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@Tag(name = "Pedido", description = "Endpoints relacionados ao Pedido")
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoApi {
    @Autowired
    private IniciaPedidoUseCase iniciaPedidoUseCase;

    @Autowired
    private ListaPedidosUseCase listaPedidosUseCase;

    @Autowired
    private AdicionaProdutoAoPedidoUseCase adicionaProdutoAoPedidoUseCase;

    @Autowired
    private BuscaProdutoUseCase buscaProdutoUseCase;

    @Autowired
    private RemoveProdutoPedidoUseCase removeProdutoPedidoUseCase;

    @Autowired
    private AvancaStatusPedidoUseCase avancaStatusPedidoUseCase;

    @Autowired
    private FecharPedidoUseCase fecharPedidoUseCase;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Operation(
            summary = "Inicia um novo Pedido",
            description = "Realiza um inicio de um novo pedido, com cpf opcional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado")
    })
    @PostMapping
    public ResponseEntity<PedidoResponse> inicia(@RequestParam(defaultValue = "") String cpf) {
        var pedidoResponse = PedidoController.iniciar(cpf, iniciaPedidoUseCase, pedidoMapper);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedidoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(pedidoResponse);
    }

    @Operation(
            summary = "Listar todos os pedidos",
            description = "Realiza a listagem de todos os pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso")
    })
    @GetMapping
    public ResponseEntity<Set<PedidoResponse>> listarTodos() {
        var pedidos = PedidoController.listarTodos(listaPedidosUseCase, pedidoMapper);
        return ResponseEntity.ok().body(pedidos);
    }

    @Operation(
            summary = "Listar todos os pedidos pagos numa fila",
            description = "Realiza a listagem de todos os pedidos pagos numa fila ordenado pela hora de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso")
    })
    @GetMapping("/fila")
    public ResponseEntity<Set<PedidoResponse>> listarPedidosNaFila() {
        var pedidos = PedidoController.listarPedidosNaFila(listaPedidosUseCase, pedidoMapper);
        return ResponseEntity.ok().body(pedidos);
    }

    @Operation(
            summary = "Adição de Produto em um Pedido",
            description = "Realiza a adição de produtos a um Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponse> adicionaProduto(@PathVariable Long id,
                                                          @RequestBody AdicionaProdutoAoPedidoRequest adicionaProdutoAoPedidoRequest) {
        var pedidoResponse = PedidoController.adicionarProduto(id,
                adicionaProdutoAoPedidoRequest,
                adicionaProdutoAoPedidoUseCase,
                buscaProdutoUseCase,
                pedidoMapper);
        return ResponseEntity.ok().body(pedidoResponse);
    }

    @Operation(
            summary = "Remoção de Produto em um Pedido",
            description = "Realiza a remoção de produto a um Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido removido com sucesso")
    })
    @PatchMapping("/{id}/produtos/{idProduto}")
    public ResponseEntity<PedidoResponse> remove(@PathVariable Long id,
                                                 @PathVariable Long idProduto,
                                                 @RequestParam(defaultValue = "1") int quantidade) {
        var pedidoResponse = PedidoController.remover(id, idProduto, quantidade, removeProdutoPedidoUseCase, pedidoMapper);
        return ResponseEntity.ok().body(pedidoResponse);
    }

    @Operation(
            summary = "Avança Status de um Pedido",
            description = "Realiza o avanço de um Pedido para o próximo Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido avançado com sucesso")
    })
    @PatchMapping("/{id}/avanca")
    public ResponseEntity<PedidoResponse> avancaStatus(@PathVariable Long id) {
        var pedidoResponse = PedidoController.avancarStatus(id, avancaStatusPedidoUseCase, pedidoMapper);
        return ResponseEntity.ok().body(pedidoResponse);
    }

    @Operation(
            summary = "Fecha pedido",
            description = "Fecha pedido para que seja gerado QR Code de Pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido fechado com sucesso")
    })
    @PatchMapping("/{id}/fecha")
    public ResponseEntity<PedidoResponse> fecha(@PathVariable Long id) {
        var pedidoResponse = PedidoController.fechar(id, fecharPedidoUseCase, pedidoMapper);
        return ResponseEntity.ok().body(pedidoResponse);
    }

}
