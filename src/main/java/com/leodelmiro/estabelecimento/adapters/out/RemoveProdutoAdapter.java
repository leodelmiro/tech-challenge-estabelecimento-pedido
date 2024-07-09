package com.leodelmiro.estabelecimento.adapters.out;

import com.leodelmiro.estabelecimento.adapters.out.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.application.ports.out.RemoveProdutoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveProdutoAdapter implements RemoveProdutoOutputPort {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}
