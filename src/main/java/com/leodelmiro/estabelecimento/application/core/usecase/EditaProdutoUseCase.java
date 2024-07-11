package com.leodelmiro.estabelecimento.application.core.usecase;

import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.in.BuscaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.in.EditaProdutoInputPort;
import com.leodelmiro.estabelecimento.application.ports.out.EditaProdutoOutputPort;

public class EditaProdutoUseCase implements EditaProdutoInputPort {

    private final EditaProdutoOutputPort editaProdutoOutputPort;
    private final BuscaProdutoInputPort buscaProdutoInputPort;

    public EditaProdutoUseCase(EditaProdutoOutputPort editaProdutoOutputPort,
                               BuscaProdutoInputPort buscaProdutoInputPort) {
        this.editaProdutoOutputPort = editaProdutoOutputPort;
        this.buscaProdutoInputPort = buscaProdutoInputPort;
    }

    @Override
    public Produto editar(Produto produto, Long id) {
        buscaProdutoInputPort.buscar(id);
        return editaProdutoOutputPort.editar(produto, id);
    }
}
