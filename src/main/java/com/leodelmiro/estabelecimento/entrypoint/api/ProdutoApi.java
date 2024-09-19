package com.leodelmiro.estabelecimento.entrypoint.api;

import com.leodelmiro.estabelecimento.core.usecase.produto.*;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.request.CadastraProdutoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.entrypoint.controller.ProdutoController;
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

@Tag(name = "Produto", description = "Endpoints relacionados ao Produto")
@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoApi {
    @Autowired
    private CadastraProdutoUseCase cadastraProdutoUseCase;

    @Autowired
    private RemoveProdutoUseCase removeProdutoUseCase;

    @Autowired
    private BuscaProdutoUseCase buscaProdutoUseCase;

    @Autowired
    private ListaProdutosUseCase listaProdutosUseCase;

    @Autowired
    private EditaProdutoUseCase editaProdutoUseCase;

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
        var produtoResponse = ProdutoController.cadastrar(cadastraProdutoRequest, cadastraProdutoUseCase, produtoMapper);
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
            var produtoResponse = ProdutoController.buscar(id, buscaProdutoUseCase, produtoMapper);
            return ResponseEntity.ok().body(produtoResponse);
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
        var produtos = ProdutoController.listarTodos(listaProdutosUseCase, produtoMapper);
        return ResponseEntity.ok().body(produtos);
    }


    @Operation(
            summary = "Lista todos os Produtos do tipo Lanche",
            description = "Realiza a listagem de todos os produtos do tipo Lanche")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/lanches")
    public ResponseEntity<Set<ProdutoResponse>> listarLanches() {
        var produtos = ProdutoController.listarLanches(listaProdutosUseCase, produtoMapper);
        return ResponseEntity.ok().body(produtos);
    }

    @Operation(
            summary = "Lista todos os Produtos do tipo Acompanhamento",
            description = "Realiza a listagem de todos os produtos do tipo Acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/acompanhamentos")
    public ResponseEntity<Set<ProdutoResponse>> listarAcompanhamentos() {
        var produtos = ProdutoController.listarAcompanhamentos(listaProdutosUseCase, produtoMapper);
        return ResponseEntity.ok().body(produtos);
    }

    @Operation(
            summary = "Lista todos os Produtos do tipo Bebida",
            description = "Realiza a listagem de todos os produtos do tipo Bebida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/bebidas")
    public ResponseEntity<Set<ProdutoResponse>> listarBebidas() {
        var produtos = ProdutoController.listarBebidas(listaProdutosUseCase, produtoMapper);
        return ResponseEntity.ok().body(produtos);
    }

    @Operation(
            summary = "Lista todos os Produtos do tipo Sobremesa",
            description = "Realiza a listagem de todos os produtos do tipo Sobremesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
    })
    @GetMapping("/sobremesas")
    public ResponseEntity<Set<ProdutoResponse>> listarSobremesas() {
        var produtos = ProdutoController.listarSobremesas(listaProdutosUseCase, produtoMapper);
        return ResponseEntity.ok().body(produtos);
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
            var produtoResponse = ProdutoController.editar(id, cadastraProdutoRequest, editaProdutoUseCase, produtoMapper);
            return ResponseEntity.ok().body(produtoResponse);
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
    public ResponseEntity<Void> remover(@PathVariable final Long id) {
        ProdutoController.remover(id, removeProdutoUseCase);
        return ResponseEntity.noContent().build();
    }
}
