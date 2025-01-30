package com.leodelmiro.pedido.entrypoint.controller;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.usecase.pedido.*;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.pedido.entrypoint.api.mapper.PedidoMapper;
import com.leodelmiro.pedido.entrypoint.api.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.pedido.entrypoint.api.response.PedidoResponse;
import com.leodelmiro.pedido.entrypoint.presenter.PedidoPresenter;

import java.util.Set;

public class PedidoController {

    public static PedidoResponse iniciar(String cpf,
                                         IniciaPedidoUseCase iniciaPedidoUseCase,
                                         PedidoMapper pedidoMapper) {
        var pedido = iniciaPedidoUseCase.iniciar((cpf.isEmpty() || cpf.equals("false")) ? null : cpf);
        return pedidoMapper.toPedidoResponse(pedido);
    }

    public static Set<PedidoResponse> listarTodos(ListaPedidosUseCase listaPedidosUseCase,
                                                  PedidoMapper pedidoMapper) {
        var pedidos = listaPedidosUseCase.buscar();
        return PedidoPresenter.transformarSetPedidosParaPedidosResponse(pedidos, pedidoMapper);
    }

    public static Set<PedidoResponse> listarPedidosNaFila(ListaPedidosUseCase listaPedidosUseCase,
                                                          PedidoMapper pedidoMapper) {
        var pedidos = listaPedidosUseCase.listarPedidosNaFila();
        return PedidoPresenter.transformarSetPedidosParaPedidosResponse(pedidos, pedidoMapper);
    }

    public static PedidoResponse adicionarProduto(Long id,
                                                  AdicionaProdutoAoPedidoRequest adicionaProdutoAoPedidoRequest,
                                                  AdicionaProdutoAoPedidoUseCase adicionaProdutoAoPedidoUseCase,
                                                  BuscaProdutoUseCase buscaProdutoUseCase,
                                                  PedidoMapper pedidoMapper) {

        var itensPedido = adicionaProdutoAoPedidoRequest.itens().stream().map(
                item -> {
                    var produto = buscaProdutoUseCase.buscar(item.idProduto());
                    return new ItemPedido(produto.getId(), item.quantidade());
                }
        ).toList();
        var pedido = adicionaProdutoAoPedidoUseCase.adicionar(id, itensPedido);
        return pedidoMapper.toPedidoResponse(pedido);
    }

    public static PedidoResponse remover(Long id,
                                         Long idProduto,
                                         int quantidade,
                                         RemoveProdutoPedidoUseCase removeProdutoPedidoUseCase,
                                         PedidoMapper pedidoMapper) {
        var pedido = removeProdutoPedidoUseCase.remover(id, idProduto, quantidade);
        return pedidoMapper.toPedidoResponse(pedido);
    }


    public static PedidoResponse avancarStatus(Long id,
                                               AvancaStatusPedidoUseCase avancaStatusPedidoUseCase,
                                               PedidoMapper pedidoMapper) {
        var pedido = avancaStatusPedidoUseCase.avancar(id);
        return pedidoMapper.toPedidoResponse(pedido);
    }

    public static PedidoResponse fechar(Long id,
                                        FecharPedidoUseCase fecharPedidoUseCase,
                                        PedidoMapper pedidoMapper) {
        var pedido = fecharPedidoUseCase.fechar(id);
        return pedidoMapper.toPedidoResponse(pedido);
    }
}
