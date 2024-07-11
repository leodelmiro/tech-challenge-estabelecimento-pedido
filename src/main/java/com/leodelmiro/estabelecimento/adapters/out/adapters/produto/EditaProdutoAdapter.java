package com.leodelmiro.estabelecimento.adapters.out.adapters.produto;

import com.leodelmiro.estabelecimento.adapters.out.repository.ProdutoRepository;
import com.leodelmiro.estabelecimento.adapters.out.repository.mapper.ProdutoEntityMapper;
import com.leodelmiro.estabelecimento.application.core.domain.Produto;
import com.leodelmiro.estabelecimento.application.ports.out.produto.EditaProdutoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditaProdutoAdapter implements EditaProdutoOutputPort {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEntityMapper produtoEntityMapper;

    @Override
    public Produto editar(Produto produto, Long id) {
        var produtoASerEditado = produtoEntityMapper.toProdutoEntity(produto);
        produtoASerEditado.getImagens().forEach(imagemEntity -> imagemEntity.setProduto(produtoASerEditado));
        produtoASerEditado.setId(id);
        var produtoEditado = produtoRepository.save(produtoASerEditado);
        return produtoEntityMapper.toProduto(produtoEditado);
    }

}
