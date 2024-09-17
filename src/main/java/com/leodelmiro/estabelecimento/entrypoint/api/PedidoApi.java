package com.leodelmiro.estabelecimento.entrypoint.api;

import com.leodelmiro.estabelecimento.entrypoint.api.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.PedidoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.response.PedidoResponse;
import com.leodelmiro.estabelecimento.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.*;
import com.leodelmiro.estabelecimento.core.usecase.pedido.*;
import com.leodelmiro.estabelecimento.core.usecase.produto.BuscaProdutoUseCase;
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
import java.util.stream.Collectors;

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
    private PedidoMapper pedidoMapper;

    @Operation(
            summary = "Inicia um novo Pedido",
            description = "Realiza um inicio de um novo pedido, com cpf opcional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado")
    })
    @PostMapping
    public ResponseEntity<PedidoResponse> inicia(@RequestParam(defaultValue = "") String cpf) {
        var pedido = iniciaPedidoUseCase.iniciar(cpf);
        var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
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
        var pedidos = listaPedidosUseCase.buscar();
        return ResponseEntity.ok().body(transformarSetPedidosParaPedidosResponse(pedidos));
    }

    @Operation(
            summary = "Listar todos os pedidos pagos numa fila",
            description = "Realiza a listagem de todos os pedidos pagos numa fila ordenado pela hora de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso")
    })
    @GetMapping("/fila")
    public ResponseEntity<Set<PedidoResponse>> listarPedidosNaFila() {
        var pedidos = listaPedidosUseCase.listarPedidosNaFila();
        return ResponseEntity.ok().body(transformarSetPedidosParaPedidosResponse(pedidos));
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
        var itensPedido = adicionaProdutoAoPedidoRequest.itens().stream().map(
                item -> {
                    var produto = buscaProdutoUseCase.buscar(item.idProduto());
                    return new ItemPedido(produto, item.quantidade());
                }
        ).toList();
        var pedido = adicionaProdutoAoPedidoUseCase.adicionar(id, itensPedido);
        var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
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
        var pedido = removeProdutoPedidoUseCase.remover(id, idProduto, quantidade);
        var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
        return ResponseEntity.ok().body(pedidoResponse);
    }

    @Operation(
            summary = "Avança Status de um Pedido",
            description = "Realiza o avanço de um Pedido para o próximo Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido removido com sucesso")
    })
    @PatchMapping("/{id}/avanca")
    public ResponseEntity<PedidoResponse> avancaStatus(@PathVariable Long id) {
        var pedido = avancaStatusPedidoUseCase.avancar(id);
        var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
        return ResponseEntity.ok().body(pedidoResponse);
    }

    private Set<PedidoResponse> transformarSetPedidosParaPedidosResponse(Set<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> pedidoMapper.toPedidoResponse(pedido))
                .collect(Collectors.toSet()
                );
    }
}
