package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.ProdutoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.ports.in.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.CriaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.RemoveProdutoInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

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
    private ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest produtoRequest) {
        var produto = produtoMapper.toProduto(produtoRequest);
        var produtoCriado = criaProdutoInputPort.criar(produto);
        var produtoResponse = produtoMapper.toProdutoResponse(produtoCriado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produtoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(produtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable final Long id) {
        try {
            var produto = buscaProdutoInputPort.busca(id);
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
