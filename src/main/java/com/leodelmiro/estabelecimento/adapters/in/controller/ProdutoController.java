package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.CriaProdutoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    @Autowired
    private CriaProdutoInputPort criaProdutoInputPort;

    @Autowired
    private RemoveProdutoInputPort removeProdutoInputPort;

    @Autowired
    private BuscaProdutoInputPort buscaProdutoInputPort;

    @Autowired
    private BuscaProdutosInputPort buscaProdutosInputPort;

    @Autowired
    private EditaProdutoInputPort editaProdutoInputPort;

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody CriaProdutoRequest criaProdutoRequest) {
        var produto = produtoMapper.toProduto(criaProdutoRequest);
        var produtoCriado = criaProdutoInputPort.criar(produto);
        var produtoResponse = produtoMapper.toProdutoResponse(produtoCriado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produtoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(produtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable final Long id) {
        try {
            var produto = buscaProdutoInputPort.buscar(id);
            return ResponseEntity.ok().body(produtoMapper.toProdutoResponse(produto));
        } catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Set<ProdutoResponse>> buscarTodos() {
        var produtos = buscaProdutosInputPort.buscarTodos();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }


    @GetMapping("/lanches")
    public ResponseEntity<Set<ProdutoResponse>> buscarLanches() {
        var produtos = buscaProdutosInputPort.buscarPorLanches();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @GetMapping("/acompanhamentos")
    public ResponseEntity<Set<ProdutoResponse>> buscarAcompanhamentos() {
        var produtos = buscaProdutosInputPort.buscarPorAcompanhamentos();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @GetMapping("/bebidas")
    public ResponseEntity<Set<ProdutoResponse>> buscarBebidas() {
        var produtos = buscaProdutosInputPort.buscarPorBebidas();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @GetMapping("/sobremesas")
    public ResponseEntity<Set<ProdutoResponse>> buscarSobremesas() {
        var produtos = buscaProdutosInputPort.buscarPorSobremesas();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    private Set<ProdutoResponse> transformarSetProdutosParaProdutoResponse(Set<Produto> produtos) {
        return produtos.stream()
                .map(produto -> produtoMapper.toProdutoResponse(produto))
                .collect(Collectors.toSet()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> editar(@PathVariable final Long id, @Valid @RequestBody CriaProdutoRequest criaProdutoRequest) {
        try {
            var produto = produtoMapper.toProduto(criaProdutoRequest);
            produto = editaProdutoInputPort.editar(produto, id);
            return ResponseEntity.ok().body(produtoMapper.toProdutoResponse(produto));
        } catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponse> remover(@PathVariable final Long id) {
        removeProdutoInputPort.remover(id);
        return ResponseEntity.noContent().build();
    }
}
