package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.ProdutoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.request.ProdutoRequest;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.ProdutoResponse;
import com.leodelmiro.estabelecimento.application.ports.in.CriaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.RemoveProdutoInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    @Autowired
    private CriaProdutoInputPort criaProdutoInputPort;

    @Autowired
    private RemoveProdutoInputPort removeProdutoInputPort;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponse> remover(@PathVariable final Long id) {
        removeProdutoInputPort.remover(id);
        return ResponseEntity.noContent().build();
    }
}
