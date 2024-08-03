package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.PedidoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.PedidoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.*;
import com.leodelmiro.estabelecimento.application.ports.in.produto.BuscaProdutoInputPort;
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
public class PedidoController {
    @Autowired
    private IniciaPedidoInputPort iniciaPedidoInputPort;

    @Autowired
    private ListaPedidosInputPort listaPedidosInputPort;

    @Autowired
    private AdicionaProdutoAoPedidoInputPort adicionaProdutoAoPedidoInputPort;

    @Autowired
    private BuscaProdutoInputPort buscaProdutoInputPort;

    @Autowired
    private RemoveProdutoPedidoInputPort removeProdutoPedidoInputPort;

    @Autowired
    private AvancaStatusPedidoInputPort avancaStatusPedidoInputPort;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Operation(
            summary = "Inicia um novo Pedido",
            description = "Realiza um inicio de um novo pedido, com cpf opcional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado")
    })
    @PostMapping("/inicia")
    public ResponseEntity<PedidoResponse> inicia(@RequestParam(defaultValue = "") String cpf) {
        var pedido = iniciaPedidoInputPort.iniciar(cpf);
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
        var pedidos = listaPedidosInputPort.buscar();
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
        var pedidos = listaPedidosInputPort.listarPedidosNaFila();
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
                    var produto = buscaProdutoInputPort.buscar(item.idProduto());
                    return new ItemPedido(produto, item.quantidade());
                }
        ).toList();
        var pedido = adicionaProdutoAoPedidoInputPort.adicionar(id, itensPedido);
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
        var pedido = removeProdutoPedidoInputPort.remover(id, idProduto, quantidade);
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
        var pedido = avancaStatusPedidoInputPort.avancar(id);
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
