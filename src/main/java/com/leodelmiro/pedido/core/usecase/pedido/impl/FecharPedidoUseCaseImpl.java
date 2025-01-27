package com.leodelmiro.pedido.core.usecase.pedido.impl;

import com.leodelmiro.pedido.core.dataprovider.pedido.FecharPedidoGateway;
import com.leodelmiro.pedido.core.dataprovider.pedido.GerarOrdemPagamentoGateway;
import com.leodelmiro.pedido.core.domain.Pedido;
import com.leodelmiro.pedido.core.usecase.pedido.BuscaPedidoUseCase;
import com.leodelmiro.pedido.core.usecase.pedido.FecharPedidoUseCase;

import static com.leodelmiro.pedido.core.domain.StatusPedido.PENDENTE_FECHAMENTO;

public class FecharPedidoUseCaseImpl implements FecharPedidoUseCase {

    private final FecharPedidoGateway fecharPedidoGateway;
    private final GerarOrdemPagamentoGateway gerarQrCodeGateway;
    private final BuscaPedidoUseCase buscaPedidoUseCase;

    public FecharPedidoUseCaseImpl(FecharPedidoGateway fecharPedidoGateway,
                                   GerarOrdemPagamentoGateway gerarQrCodeGateway,
                                   BuscaPedidoUseCase buscaPedidoUseCase) {
        this.fecharPedidoGateway = fecharPedidoGateway;
        this.gerarQrCodeGateway = gerarQrCodeGateway;
        this.buscaPedidoUseCase = buscaPedidoUseCase;
    }

    @Override
    public Pedido fechar(Long id) {
        var pedidoAAvancar = buscaPedidoUseCase.buscar(id);
        validarPedido(pedidoAAvancar);
        pedidoAAvancar.setOrdemPagamentoId(gerarQrCodeGateway.gerar(pedidoAAvancar));
        pedidoAAvancar.avancarStatus();
        return fecharPedidoGateway.fechar(pedidoAAvancar);
    }

    private void validarPedido(Pedido pedidoAFechar) {
        if (isPedidoSemItens(pedidoAFechar)) throw new IllegalStateException("Impossível fechar pedido sem itens");
        if (isNotPedenteFechamento(pedidoAFechar))
            throw new IllegalStateException("Impossível avancar pedido com status diferente de Pendente de Fechamento");
    }

    private boolean isNotPedenteFechamento(Pedido pedido) {
        return pedido.getStatus() != PENDENTE_FECHAMENTO;
    }

    private boolean isPedidoSemItens(Pedido pedido) {
        return pedido.getItens().isEmpty();
    }
}