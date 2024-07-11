package com.leodelmiro.estabelecimento.application.core.usecase.produto;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.produto.CadastraProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.produto.CadastraProdutoOutputPort;

public class CadastraProdutoUseCase implements CadastraProdutoInputPort {

    private final CadastraProdutoOutputPort cadastraProdutoOutputPort;

    public CadastraProdutoUseCase(CadastraProdutoOutputPort cadastraProdutoOutputPort) {
        this.cadastraProdutoOutputPort = cadastraProdutoOutputPort;
    }

    @Override
    public Produto cadastrar(Produto produto) {
        return cadastraProdutoOutputPort.cadastrar(produto);
    }
}
