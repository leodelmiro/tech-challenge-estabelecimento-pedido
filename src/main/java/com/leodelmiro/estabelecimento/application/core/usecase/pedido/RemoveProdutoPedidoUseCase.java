package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.BuscaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.RemoveProdutoPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.produto.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.RemoveProdutoPedidoOutputPort;

import static com.leodelmiro.estabelecimento.application.core.domain.StatusPedido.PENDENTE_FECHAMENTO;

public class RemoveProdutoPedidoUseCase implements RemoveProdutoPedidoInputPort {

    private final RemoveProdutoPedidoOutputPort removeProdutoPedidoOutputPort;
    private final BuscaPedidoInputPort buscaPedidoInputPort;
    private final BuscaProdutoInputPort buscaProdutoInputPort;

    public RemoveProdutoPedidoUseCase(RemoveProdutoPedidoOutputPort removeProdutoPedidoOutputPort,
                                      BuscaPedidoInputPort buscaPedidoInputPort, BuscaProdutoInputPort buscaProdutoInputPort) {
        this.removeProdutoPedidoOutputPort = removeProdutoPedidoOutputPort;
        this.buscaPedidoInputPort = buscaPedidoInputPort;
        this.buscaProdutoInputPort = buscaProdutoInputPort;
    }

    @Override
    public Pedido remover(Long idPedido, Long idProduto, int quantidade) {
        var pedido = buscaPedidoInputPort.buscar(idPedido);
        validarPedido(pedido);
        var produto = buscaProdutoInputPort.buscar(idProduto);
        ItemPedido itemPedido = validarIdProdutoEItemDoPedido(pedido, produto);
        validarQuantidade(quantidade, itemPedido);
        atualizarProduto(quantidade, itemPedido, pedido);
        return removeProdutoPedidoOutputPort.remover(pedido);
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

    private void validarPedido(Pedido pedido) {
        if (isPedidoFechado(pedido))
            throw new IllegalStateException("Impossível adicionar produtos,pedido já foi fechado");
        if (isPedidoSemItens(pedido)) throw new IllegalStateException("Impossível remover itens de pedido vazio");
    }

    private boolean isPedidoSemItens(Pedido pedido) {
        return pedido.getItens().isEmpty();
    }

    private boolean isPedidoFechado(Pedido pedido) {
        return pedido.getStatus() != PENDENTE_FECHAMENTO;
    }
}