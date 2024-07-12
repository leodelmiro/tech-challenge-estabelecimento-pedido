package com.leodelmiro.estabelecimento.adapters.in.controller;

import com.leodelmiro.estabelecimento.adapters.in.controller.mapper.PedidoMapper;
import com.leodelmiro.estabelecimento.adapters.in.controller.response.PedidoResponse;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.IniciaPedidoInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    private IniciaPedidoInputPort iniciaPedidoInputPort;

    @Autowired
    private PedidoMapper pedidoMapper;

    @PostMapping("/{cpf}/inicia")
    public ResponseEntity<PedidoResponse> inicia(@PathVariable String cpf) {
        try {
            var pedido = iniciaPedidoInputPort.iniciar(cpf);
            var pedidoResponse = pedidoMapper.toPedidoResponse(pedido);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(pedidoResponse.id()).toUri();
            return ResponseEntity.created(uri).body(pedidoResponse);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
