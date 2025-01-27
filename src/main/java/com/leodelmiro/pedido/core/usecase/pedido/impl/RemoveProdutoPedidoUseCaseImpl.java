package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.pedido.RemoveProdutoPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.produto.BuscaProdutoUseCase;
import com.leodelmiro.pedido.core.dataprovider.pedido.RemoveProdutoPedidoGateway;

public class RemoveProdutoPedidoUseCaseImpl implements RemoveProdutoPedidoUseCase {

    private final RemoveProdutoPedidoGateway removeProdutoPedidoGateway;
    private final BuscaPedidoUseCase buscaPedidoUseCase;
    private final BuscaProdutoUseCase buscaProdutoUseCase;

    public RemoveProdutoPedidoUseCaseImpl(RemoveProdutoPedidoGateway removeProdutoPedidoGateway,
                                          BuscaPedidoUseCase buscaPedidoUseCase, BuscaProdutoUseCase buscaProdutoUseCase) {
        this.removeProdutoPedidoGateway = removeProdutoPedidoGateway;
        this.buscaPedidoUseCase = buscaPedidoUseCase;
        this.buscaProdutoUseCase = buscaProdutoUseCase;
    }

    @Override
    public Pedido remover(Long idPedido, Long idProduto, int quantidade) {
        var pedido = buscaPedidoUseCase.buscar(idPedido);
        var produto = buscaProdutoUseCase.buscar(idProduto);
        ItemPedido itemPedido = validarIdProdutoEItemDoPedido(pedido, produto);
        validarQuantidade(quantidade, itemPedido);
        atualizarProduto(quantidade, itemPedido, pedido);
        return removeProdutoPedidoGateway.remover(pedido);
    }

    private void atualizarProduto(int quantidade, ItemPedido itemPedido, Pedido pedido) {
        if (isQuantidadeIgualQuantidadePedido(quantidade, itemPedido)) {
            pedido.removeItem(itemPedido);
        } else {
            pedido.atualizaQuantidadeItemPedido(itemPedido.getId(), itemPedido.getQuantidade() - quantidade);
        }
    }

    private boolean isQuantidadeIgualQuantidadePedido(int quantidade, ItemPedido itemPedido) {
        return quantidade == itemPedido.getQuantidade();
    }

    private void validarQuantidade(int quantidade, ItemPedido itemPedido) {
        if (quantidade > itemPedido.getQuantidade())
            throw new IllegalArgumentException("Quantidade maior do que a quantidade no pedido");
    }

    private ItemPedido validarIdProdutoEItemDoPedido(Pedido pedido, Produto produto) {
        return pedido.getItens()
                .stream()
                .filter(item -> item.getProduto().getId().equals(produto.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id produto não corresponde a um item do pedido informado"));
    }
}