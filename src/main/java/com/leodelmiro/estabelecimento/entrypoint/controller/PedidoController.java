package com.leodelmiro.estabelecimento.entrypoint.controller;

import com.leodelmiro.estabelecimento.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.usecase.pedido.*;
import com.leodelmiro.estabelecimento.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.estabelecimento.entrypoint.api.mapper.PedidoMapper;
import com.leodelmiro.estabelecimento.entrypoint.api.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.estabelecimento.entrypoint.api.response.PedidoResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class PedidoController {

    public static PedidoResponse iniciar(String cpf,
                                         IniciaPedidoUseCase iniciaPedidoUseCase,
                                         PedidoMapper pedidoMapper) {
        // TODO VALIDAR CPF
        var pedido = iniciaPedidoUseCase.iniciar(cpf);
        return pedidoMapper.toPedidoResponse(pedido);
    }

    public static Set<PedidoResponse> listarTodos(ListaPedidosUseCase listaPedidosUseCase,
                                                  PedidoMapper pedidoMapper) {
        var pedidos = listaPedidosUseCase.buscar();
        return transformarSetPedidosParaPedidosResponse(pedidos, pedidoMapper);
    }

    public static Set<PedidoResponse> listarPedidosNaFila(ListaPedidosUseCase listaPedidosUseCase,
                                                          PedidoMapper pedidoMapper) {
        // TODO QUEBRAR PARA USE CASE FILA
        var pedidos = listaPedidosUseCase.listarPedidosNaFila();
        return transformarSetPedidosParaPedidosResponse(pedidos, pedidoMapper);
    }

    public static PedidoResponse adicionarProduto(Long id,
                                                  AdicionaProdutoAoPedidoRequest adicionaProdutoAoPedidoRequest,
                                                  AdicionaProdutoAoPedidoUseCase adicionaProdutoAoPedidoUseCase,
                                                  BuscaProdutoUseCase buscaProdutoUseCase,
                                                  PedidoMapper pedidoMapper) {

        var itensPedido = adicionaProdutoAoPedidoRequest.itens().stream().map(
                item -> {
                    var produto = buscaProdutoUseCase.buscar(item.idProduto());
                    return new ItemPedido(produto, item.quantidade());
                }
        ).toList();
        // TODO VALIDAR PEDIDO
        var pedido = adicionaProdutoAoPedidoUseCase.adicionar(id, itensPedido);
        return pedidoMapper.toPedidoResponse(pedido);
    }

    public static PedidoResponse remover(Long id,
                                         Long idProduto,
                                         int quantidade,
                                         RemoveProdutoPedidoUseCase removeProdutoPedidoUseCase,
                                         PedidoMapper pedidoMapper) {
        // TODO VALIDAR DADOS NO PEDIDO
        var pedido = removeProdutoPedidoUseCase.remover(id, idProduto, quantidade);
        return pedidoMapper.toPedidoResponse(pedido);
    }


    public static PedidoResponse avancaStatus(Long id,
                                              AvancaStatusPedidoUseCase avancaStatusPedidoUseCase,
                                              PedidoMapper pedidoMapper) {
        var pedido = avancaStatusPedidoUseCase.avancar(id);
        return pedidoMapper.toPedidoResponse(pedido);
    }

    // TODO TRANSFORMAR PARA PRESENTER
    private static Set<PedidoResponse> transformarSetPedidosParaPedidosResponse(Set<Pedido> pedidos, PedidoMapper pedidoMapper) {
        return pedidos.stream()
                .map(pedidoMapper::toPedidoResponse)
                .collect(Collectors.toSet()
                );
    }
}
