package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.PedidoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.PedidoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.*;
import com.leodelmiro.estabelecimento.application.ports.in.produto.BuscaProdutoInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PostMapping("/inicia/cliente/{cpf}")
    public ResponseEntity<PedidoResponse> inicia(@PathVariable String cpf) {
        try {
            var pedido = iniciaPedidoInputPort.iniciar(cpf);
            var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(pedidoResponse.id()).toUri();
            return ResponseEntity.created(uri).body(pedidoResponse);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Set<PedidoResponse>> listarTodos() {
        var pedidos = listaPedidosInputPort.buscar();
        return ResponseEntity.ok().body(transformarSetPedidosParaPedidosResponse(pedidos));
    }

    @GetMapping("/fila")
    public ResponseEntity<Set<PedidoResponse>> listarPedidosNaFila() {
        var pedidos = listaPedidosInputPort.listarPedidosNaFila();
        return ResponseEntity.ok().body(transformarSetPedidosParaPedidosResponse(pedidos));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponse> adicionaProduto(@PathVariable Long id,
                                                          @RequestBody AdicionaProdutoAoPedidoRequest adicionaProdutoAoPedidoRequest) {
        try {
            var itensPedido = adicionaProdutoAoPedidoRequest.itens().stream().map(
                    item -> {
                        var produto = buscaProdutoInputPort.buscar(item.idProduto());
                        return new ItemPedido(produto, item.quantidade());
                    }
            ).toList();
            var pedido = adicionaProdutoAoPedidoInputPort.adicionar(id, itensPedido);
            var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
            return ResponseEntity.ok().body(pedidoResponse);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/produtos/{idProduto}")
    public ResponseEntity<PedidoResponse> remove(@PathVariable Long id, @PathVariable Long idProduto, @RequestParam int quantidade) {
        try {
            var pedido = removeProdutoPedidoInputPort.remover(id, idProduto, quantidade);
            var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
            return ResponseEntity.ok().body(pedidoResponse);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/avanca")
    public ResponseEntity<PedidoResponse> avancaStatus(@PathVariable Long id) {
        try {
            var pedido = avancaStatusPedidoInputPort.avancar(id);
            var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
            return ResponseEntity.ok().body(pedidoResponse);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    private Set<PedidoResponse> transformarSetPedidosParaPedidosResponse(Set<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> pedidoMapper.toPedidoResponse(pedido))
                .collect(Collectors.toSet()
                );
    }
}
