package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.AdicionaProdutoAoPedidoGateway;
import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.usecase.pedido.AdicionaProdutoAoPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;

import java.math.BigDecimal;
import java.util.List;

public class AdicionaProdutoAoPedidoUseCaseImpl implements AdicionaProdutoAoPedidoUseCase {

    private final AdicionaProdutoAoPedidoGateway adicionaProdutoAoPedidoGateway;
    private final BuscaPedidoUseCase buscaPedidoUseCase;

    public AdicionaProdutoAoPedidoUseCaseImpl(AdicionaProdutoAoPedidoGateway adicionaProdutoAoPedidoGateway,
                                              BuscaPedidoUseCase buscaPedidoUseCase) {
        this.adicionaProdutoAoPedidoGateway = adicionaProdutoAoPedidoGateway;
        this.buscaPedidoUseCase = buscaPedidoUseCase;
    }

    @Override
    public Pedido adicionar(Long idPedido, List<ItemPedido> itens) {
        var pedido = buscaPedidoUseCase.buscar(idPedido);
        pedido.addItens(itens);
        atualizarPrecoTotal(itens, pedido);
        atualizarTempoTotalDePreparo(itens, pedido);
        return adicionaProdutoAoPedidoGateway.adicionar(pedido);
    }

    private void atualizarTempoTotalDePreparo(List<ItemPedido> itens, Pedido pedido) {
        pedido.setTempoTotalDePreparoEmSegundos(itens.stream().mapToLong(
                        item ->
                                item
                                        .getProduto()
                                        .getTempoDePreparoEmSegundos()
                                        * item.getQuantidade()
                )
                .sum());
    }

    private void atualizarPrecoTotal(List<ItemPedido> itens, Pedido pedido) {
        pedido.setPrecoTotal(itens.stream().map(
                        item ->
                                item
                                        .getProduto()
                                        .getPreco()
                                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}