package com.leodelmiro.estabelecimento.application.core.usecase.pedido;

import com.leodelmiro.estabelecimento.application.core.domain.ItemPedido;
import com.leodelmiro.estabelecimento.application.core.domain.Pedido;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.AdicionaProdutoAoPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.pedido.BuscaPedidoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.produto.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.pedido.AdicionaProdutoAoPedidoOutputPort;

import java.math.BigDecimal;
import java.util.List;

import static com.leodelmiro.estabelecimento.application.core.domain.StatusPedido.PENDENTE_FECHAMENTO;

public class AdicionaProdutoAoPedidoUseCase implements AdicionaProdutoAoPedidoInputPort {

    private final AdicionaProdutoAoPedidoOutputPort adicionaProdutoAoPedidoOutputPort;
    private final BuscaPedidoInputPort buscaPedidoInputPort;

    public AdicionaProdutoAoPedidoUseCase(AdicionaProdutoAoPedidoOutputPort adicionaProdutoAoPedidoOutputPort, BuscaPedidoInputPort buscaPedidoInputPort, BuscaProdutoInputPort buscaProdutoInputPort) {
        this.adicionaProdutoAoPedidoOutputPort = adicionaProdutoAoPedidoOutputPort;
        this.buscaPedidoInputPort = buscaPedidoInputPort;
    }

    @Override
    public Pedido adicionar(Long idPedido, List<ItemPedido> itens) {
        var pedido = buscaPedidoInputPort.buscar(idPedido);
        if (pedido.getStatus() != PENDENTE_FECHAMENTO)
            throw new IllegalStateException("Impossível adicionar produtos,pedido já foi fechado");
        pedido.addItens(itens);
        pedido.setPrecoTotal(itens.stream().map(
                        item ->
                                item
                                        .getProduto()
                                        .getPreco()
                                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        pedido.setTempoTotalDePreparoEmSegundos(itens.stream().mapToLong(
                        item ->
                                item
                                        .getProduto()
                                        .getTempoDePreparoEmSegundos()
                                        * item.getQuantidade()
                )
                .sum());

        return adicionaProdutoAoPedidoOutputPort.adicionar(pedido);
    }
}