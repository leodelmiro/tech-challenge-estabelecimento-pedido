package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.CadastraProdutoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.produto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "Produto", description = "Endpoints relacionados ao Produto")
@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    @Autowired
    private CadastraProdutoInputPort cadastraProdutoInputPort;

    @Autowired
    private RemoveProdutoInputPort removeProdutoInputPort;

    @Autowired
    private BuscaProdutoInputPort buscaProdutoInputPort;

    @Autowired
    private ListaProdutosInputPort listaProdutosInputPort;

    @Autowired
    private EditaProdutoInputPort editaProdutoInputPort;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Operation(
            summary = "Cadastro de Produto",
            description = "Realiza um cadastro de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado")
    })
    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody CadastraProdutoRequest cadastraProdutoRequest) {
        var produto = produtoMapper.toProduto(cadastraProdutoRequest);
        var produtoCadastrado = cadastraProdutoInputPort.cadastrar(produto);
        var produtoResponse = produtoMapper.toProdutoResponse(produtoCadastrado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produtoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(produtoResponse);
    }

    @Operation(
            summary = "Busca de Produto por ID",
            description = "Realiza a busca de um produto por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable final Long id) {
        try {
            var produto = buscaProdutoInputPort.buscar(id);
            return ResponseEntity.ok().body(produtoMapper.toProdutoResponse(produto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Listar todos os produtos",
            description = "Realiza a listagem de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso")
    })
    @GetMapping
    public ResponseEntity<Set<ProdutoResponse>> listarTodos() {
        var produtos = listaProdutosInputPort.listarTodos();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }


    @Operation(
            summary = "Lista todos os Produtos do tipo Lanche",
            description = "Realiza a listagem de todos os produtos do tipo Lanche")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/lanches")
    public ResponseEntity<Set<ProdutoResponse>> listarLanches() {
        var produtos = listaProdutosInputPort.listarPorLanches();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @Operation(
            summary = "Lista todos os Produtos do tipo Acompanhamento",
            description = "Realiza a listagem de todos os produtos do tipo Acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/acompanhamentos")
    public ResponseEntity<Set<ProdutoResponse>> listarAcompanhamentos() {
        var produtos = listaProdutosInputPort.listarPorAcompanhamentos();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @Operation(
            summary = "Lista todos os Produtos do tipo Bebida",
            description = "Realiza a listagem de todos os produtos do tipo Bebida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/bebidas")
    public ResponseEntity<Set<ProdutoResponse>> listarBebidas() {
        var produtos = listaProdutosInputPort.listarPorBebidas();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @Operation(
            summary = "Lista todos os Produtos do tipo Sobremesa",
            description = "Realiza a listagem de todos os produtos do tipo Sobremesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/sobremesas")
    public ResponseEntity<Set<ProdutoResponse>> listarSobremesas() {
        var produtos = listaProdutosInputPort.listarPorSobremesas();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    private Set<ProdutoResponse> transformarSetProdutosParaProdutoResponse(Set<Produto> produtos) {
        return produtos.stream()
                .map(produto -> produtoMapper.toProdutoResponse(produto))
                .collect(Collectors.toSet()
                );
    }

    @Operation(
            summary = "Edição de Produto por ID",
            description = "Permite editar um produto por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto editado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> editar(@PathVariable final Long id, @Valid @RequestBody CadastraProdutoRequest cadastraProdutoRequest) {
        try {
            var produto = produtoMapper.toProduto(cadastraProdutoRequest);
            produto = editaProdutoInputPort.editar(produto, id);
            return ResponseEntity.ok().body(produtoMapper.toProdutoResponse(produto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Remoção de Produto por ID",
            description = "Permite remover um produto por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponse> remover(@PathVariable final Long id) {
        removeProdutoInputPort.remover(id);
        return ResponseEntity.noContent().build();
    }
}
