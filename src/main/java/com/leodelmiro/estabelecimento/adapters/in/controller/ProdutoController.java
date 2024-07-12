package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.CadastraProdutoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.produto.*;
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

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody CadastraProdutoRequest cadastraProdutoRequest) {
        var produto = produtoMapper.toProduto(cadastraProdutoRequest);
        var produtoCadastrado = cadastraProdutoInputPort.cadastrar(produto);
        var produtoResponse = produtoMapper.toProdutoResponse(produtoCadastrado);
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
    public ResponseEntity<Set<ProdutoResponse>> listarTodos() {
        var produtos = listaProdutosInputPort.listarTodos();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }


    @GetMapping("/lanches")
    public ResponseEntity<Set<ProdutoResponse>> listarLanches() {
        var produtos = listaProdutosInputPort.listarPorLanches();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @GetMapping("/acompanhamentos")
    public ResponseEntity<Set<ProdutoResponse>> listarAcompanhamentos() {
        var produtos = listaProdutosInputPort.listarPorAcompanhamentos();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

    @GetMapping("/bebidas")
    public ResponseEntity<Set<ProdutoResponse>> listarBebidas() {
        var produtos = listaProdutosInputPort.listarPorBebidas();
        return ResponseEntity.ok().body(transformarSetProdutosParaProdutoResponse(produtos));
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> editar(@PathVariable final Long id, @Valid @RequestBody CadastraProdutoRequest cadastraProdutoRequest) {
        try {
            var produto = produtoMapper.toProduto(cadastraProdutoRequest);
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
