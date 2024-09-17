package com.leodelmiro.estabelecimento.core.usecase.pedido.impl;

import com.leodelmiro.estabelecimento.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.usecase.pedido.AdicionaProdutoAoPedidoUseCase;
import com.leodelmiro.estabelecimento.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.estabelecimento.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.estabelecimento.core.dataprovider.pedido.AdicionaProdutoAoPedidoGateway;

import java.math.BigDecimal;
import java.util.List;

import static com.leodelmiro.estabelecimento.core.domain.StatusPedido.PENDENTE_FECHAMENTO;


public class AdicionaProdutoAoPedidoUseCaseImpl implements AdicionaProdutoAoPedidoUseCase {

    private final AdicionaProdutoAoPedidoGateway adicionaProdutoAoPedidoGateway;
    private final BuscaPedidoUseCase buscaPedidoUseCase;

    public AdicionaProdutoAoPedidoUseCaseImpl(AdicionaProdutoAoPedidoGateway adicionaProdutoAoPedidoGateway, BuscaPedidoUseCase buscaPedidoUseCase, BuscaProdutoUseCase buscaProdutoUseCase) {
        this.adicionaProdutoAoPedidoGateway = adicionaProdutoAoPedidoGateway;
        this.buscaPedidoUseCase = buscaPedidoUseCase;
    }

    @Override
    public Pedido adicionar(Long idPedido, List<ItemPedido> itens) {
        var pedido = buscaPedidoUseCase.buscar(idPedido);
        validarPedido(pedido);
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

    private void validarPedido(Pedido pedido) {
        if (isPedidoFechado(pedido))
            throw new IllegalStateException("Impossível adicionar produtos,pedido já foi fechado");
    }

    private boolean isPedidoFechado(Pedido pedido) {
        return pedido.getStatus() != PENDENTE_FECHAMENTO;
    }
}