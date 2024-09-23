package com.leodelmiro.estabelecimento.core.usecase.pedido.impl;

import com.leodelmiro.estabelecimento.core.dataprovider.pedido.PagaPedidoGateway;
import com.leodelmiro.estabelecimento.core.domain.Pedido;
import com.leodelmiro.estabelecimento.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.estabelecimento.core.usecase.pedido.PagaPedidoUseCase;
import com.leodelmiro.estabelecimento.core.usecase.produto.BuscaProdutoUseCase;

import java.time.LocalDateTime;


public class PagaPedidoUseCaseImpl implements PagaPedidoUseCase {

    private final PagaPedidoGateway pagaPedidoGateway;
    private final BuscaPedidoUseCase buscaPedidoUseCase;

    public PagaPedidoUseCaseImpl(PagaPedidoGateway pagaPedidoGateway, BuscaPedidoUseCase buscaPedidoUseCase) {
        this.pagaPedidoGateway = pagaPedidoGateway;
        this.buscaPedidoUseCase = buscaPedidoUseCase;
    }

    @Override
    public Pedido pagar(Long idPedido, LocalDateTime pagoEm) {
        var pedido = buscaPedidoUseCase.buscar(idPedido);
        pedido.setPagoEm(pagoEm);
        return pagaPedidoGateway.pagar(pedido);
    }
}